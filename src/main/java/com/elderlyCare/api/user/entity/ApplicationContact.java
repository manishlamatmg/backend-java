package com.elderlyCare.api.user.entity;

import com.elderlyCare.api.base.IdentityNumber;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ApplicationContact extends IdentityNumber {

    private String phoneNumber;
    private String permanentAddress;
    private String localAddress;
    private String district;
    private String userName;
    private String email;
    private String message;

}
