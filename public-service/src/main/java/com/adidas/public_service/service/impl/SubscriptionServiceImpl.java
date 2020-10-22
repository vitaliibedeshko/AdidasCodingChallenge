package com.adidas.public_service.service.impl;

import com.adidas.public_service.exception.CustomGenericHttpException;
import com.adidas.public_service.request.SubscriptionRequest;
import com.adidas.public_service.response.SubscriptionResponse;
import com.adidas.public_service.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.ConnectException;
import java.text.MessageFormat;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {

    public static final String SUBSCRIPTION_RESOURCE_URI = "/api/subscription";
    public static final String TIMEOUT_ERROR_MESSAGE = "Timeout {0} while calling POST: {1}";
    public static final String SUBSCRIPTION_SERVICE_UNAVAILABLE_ERROR_MESSAGE = "Subscription service unavailable";
    public static final String ERROR_MESSAGE_FOR_4XX_AND_5XX_STATUS_CODES =
            "Error while calling endpoint POST: {} with status code {}";

    private final WebClient client;

    @Value("${subscription-service-subscribe-timeout}")
    private Long subscribeTimeout;

    public Mono<SubscriptionResponse> subscribe(SubscriptionRequest subscriptionRequest) {
        return client.post()
                .uri(SUBSCRIPTION_RESOURCE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(subscriptionRequest)
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> {
                    log.error(ERROR_MESSAGE_FOR_4XX_AND_5XX_STATUS_CODES,
                            SUBSCRIPTION_RESOURCE_URI, clientResponse.statusCode());
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorBody -> Mono.error(new CustomGenericHttpException(errorBody, clientResponse.statusCode())));
                })
                .bodyToMono(SubscriptionResponse.class)
                .onErrorResume(ConnectException.class, x -> {
                    log.error(SUBSCRIPTION_SERVICE_UNAVAILABLE_ERROR_MESSAGE);
                    return Mono.error(new CustomGenericHttpException(
                            SUBSCRIPTION_SERVICE_UNAVAILABLE_ERROR_MESSAGE,
                            HttpStatus.SERVICE_UNAVAILABLE));
                        }
                )
                .timeout(Duration.ofMillis(subscribeTimeout))
                .onErrorResume(TimeoutException.class, x -> Mono.error(new CustomGenericHttpException(
                        MessageFormat.format(TIMEOUT_ERROR_MESSAGE, subscribeTimeout, SUBSCRIPTION_RESOURCE_URI),
                        HttpStatus.SERVICE_UNAVAILABLE))
                );
    }
}
