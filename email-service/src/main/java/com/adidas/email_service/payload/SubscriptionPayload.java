package com.adidas.email_service.payload;

import com.adidas.email_service.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SubscriptionPayload {

    private Long id;

    private GenderEnum gender;

    private String firstName;

    private String email;

    private LocalDate dateOfBirth;

    private Boolean isConsent;

    private Long newsletterId;

}
