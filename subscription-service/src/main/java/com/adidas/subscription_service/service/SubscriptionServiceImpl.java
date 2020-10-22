package com.adidas.subscription_service.service;

import com.adidas.subscription_service.entity.SubscriptionEntity;
import com.adidas.subscription_service.repository.SubscriptionRepository;
import com.adidas.subscription_service.request.SubscriptionRequest;
import com.adidas.subscription_service.response.SubscriptionResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.adidas.subscription_service.config.MessagingConfig.EMAIL_SERVICE_QUEUE_NAME;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService{

    private final SubscriptionRepository subscriptionRepository;
    private final ModelMapper modelMapper;
    private final AmqpTemplate amqpTemplate;

    @Transactional
    public SubscriptionResponse subscribe(SubscriptionRequest subscriptionRequest) {
        SubscriptionEntity subscriptionEntityForSave = modelMapper.map(subscriptionRequest, SubscriptionEntity.class);
        SubscriptionEntity savedSubscriptionEntity = subscriptionRepository.saveAndFlush(subscriptionEntityForSave);
        amqpTemplate.convertAndSend(EMAIL_SERVICE_QUEUE_NAME, savedSubscriptionEntity);
        return new SubscriptionResponse(savedSubscriptionEntity.getId());
    }
}
