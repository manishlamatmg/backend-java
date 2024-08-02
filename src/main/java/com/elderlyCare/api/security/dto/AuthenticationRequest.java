package com.elderlyCare.api.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Jacksonized
public class AuthenticationRequest {

    private String email;
    private String password;
}
