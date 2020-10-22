package com.adidas.subscription_service.controller;

import com.adidas.subscription_service.request.SubscriptionRequest;
import com.adidas.subscription_service.response.SubscriptionResponse;
import com.adidas.subscription_service.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    private SubscriptionResponse subscribe(@Valid @RequestBody SubscriptionRequest subscriptionRequest) {
        return subscriptionService.subscribe(subscriptionRequest);
    }

}
