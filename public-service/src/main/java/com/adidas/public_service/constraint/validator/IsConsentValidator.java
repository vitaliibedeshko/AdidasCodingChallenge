package com.adidas.public_service.constraint.validator;

import com.adidas.public_service.constraint.IsConsentConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsConsentValidator implements ConstraintValidator<IsConsentConstraint, Boolean> {

    @Override
    public void initialize(IsConsentConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Boolean isConsentField, ConstraintValidatorContext constraintValidatorContext) {
        return isConsentField != null && isConsentField;
    }
}
