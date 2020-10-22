package com.adidas.public_service.controller;

import com.adidas.public_service.enums.GenderEnum;
import com.adidas.public_service.request.SubscriptionRequest;
import com.adidas.public_service.response.SubscriptionResponse;
import com.adidas.public_service.service.SubscriptionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static com.adidas.public_service.service.impl.SubscriptionServiceImpl.SUBSCRIPTION_RESOURCE_URI;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(SubscriptionController.class)
public class SubscriptionControllerTest {

    @MockBean
    private SubscriptionService subscriptionService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void subscribeSuccessTest() throws Exception {
        //given
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setDateOfBirth(LocalDate.of(1996, 5, 13));
        subscriptionRequest.setEmail("vitalii.test@gmail.com");
        subscriptionRequest.setFirstName("Vitalii");
        subscriptionRequest.setGender(GenderEnum.MALE);
        subscriptionRequest.setIsConsent(true);
        subscriptionRequest.setNewsletterId(32L);
        String requestAsJsonString = objectMapper.writeValueAsString(subscriptionRequest);
        SubscriptionResponse subscriptionResponse = new SubscriptionResponse();
        subscriptionResponse.setSubscriptionId(15L);
        Mockito.when(subscriptionService.subscribe(subscriptionRequest)).thenReturn(Mono.just(subscriptionResponse));
        //when
        mvc.perform(post(SUBSCRIPTION_RESOURCE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestAsJsonString))
                .andExpect(status().isCreated())
                .andReturn();
    }

}
