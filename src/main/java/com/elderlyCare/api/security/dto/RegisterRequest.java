package com.elderlyCare.api.security.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Jacksonized
@Builder
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String citizenShip;
}
