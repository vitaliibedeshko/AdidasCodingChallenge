package com.adidas.subscription_service.service;

import com.adidas.subscription_service.request.SubscriptionRequest;
import com.adidas.subscription_service.response.SubscriptionResponse;

public interface SubscriptionService {

    SubscriptionResponse subscribe(SubscriptionRequest subscriptionRequest);

}
