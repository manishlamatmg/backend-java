package com.elderlyCare.api.registration.token.entity;

import com.elderlyCare.api.base.IdentityNumber;
import com.elderlyCare.api.user.entity.ApplicationUser;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@SuperBuilder(toBuilder = true)
@Getter
@Setter
@Entity
@NoArgsConstructor
public class ConfirmationRegistrationToken extends IdentityNumber {
    private String registrationToken;
    private ZonedDateTime createdAt;
    private ZonedDateTime expiresAt;
    private ZonedDateTime confirmedAt;
    @ManyToOne
    @JoinColumn
    private ApplicationUser applicationUser;

}
