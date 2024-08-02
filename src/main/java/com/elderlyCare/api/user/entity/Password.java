package com.elderlyCare.api.user.entity;

import com.elderlyCare.api.base.IdentityNumber;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

@SuperBuilder
@Getter
@Setter
@Jacksonized
@Entity
@NoArgsConstructor
public class Password extends IdentityNumber {
    private String email;
    private String token;
    private String confirmPassword ;
    private ZonedDateTime expiryDate;
    @JoinColumn
    @ManyToOne
    private ApplicationUser applicationUser;
}
