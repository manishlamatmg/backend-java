package com.elderlyCare.api.customer.entity;

import com.elderlyCare.api.base.IdentityNumber;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerInfo extends IdentityNumber {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

}
