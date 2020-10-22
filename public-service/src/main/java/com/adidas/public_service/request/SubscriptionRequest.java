package com.adidas.public_service.request;

import com.adidas.public_service.enums.GenderEnum;
import com.adidas.public_service.constraint.IsConsentConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class SubscriptionRequest {

    private String firstName;

    private GenderEnum gender;

    @Email
    @NotNull
    private String email;

    @NotNull
    private LocalDate dateOfBirth;

    @IsConsentConstraint
    @NotNull
    private Boolean isConsent;

    @NotNull
    private Long newsletterId;

}
