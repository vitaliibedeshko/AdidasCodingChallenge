package com.adidas.subscription_service.request;

import com.adidas.subscription_service.constraint.IsConsentConstraint;
import com.adidas.subscription_service.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class SubscriptionRequest {

    private GenderEnum gender;

    private String firstName;

    @NotNull
    @Email
    private String email;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    @IsConsentConstraint
    private Boolean isConsent;

    @NotNull
    private Long newsletterId;

}
