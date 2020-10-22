package com.adidas.subscription_service.entity;

import com.adidas.subscription_service.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import java.time.LocalDate;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private GenderEnum gender;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "is_consent")
    private Boolean isConsent;

    @Column(name = "newsletter_id")
    private Long newsletterId;

}
