package com.adidas.public_service.service;

import com.adidas.public_service.request.SubscriptionRequest;
import com.adidas.public_service.response.SubscriptionResponse;
import reactor.core.publisher.Mono;

public interface SubscriptionService {

    Mono<SubscriptionResponse> subscribe(SubscriptionRequest subscriptionRequest);

}
