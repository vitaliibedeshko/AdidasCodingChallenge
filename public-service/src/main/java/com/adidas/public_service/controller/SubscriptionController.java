package com.adidas.public_service.controller;

import com.adidas.public_service.request.SubscriptionRequest;
import com.adidas.public_service.response.SubscriptionResponse;
import com.adidas.public_service.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<SubscriptionResponse> subscribe(@Valid @RequestBody SubscriptionRequest subscriptionRequest) {
        return subscriptionService.subscribe(subscriptionRequest);
    }

}
