package com.elderlyCare.api.customer.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class CustomerDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
