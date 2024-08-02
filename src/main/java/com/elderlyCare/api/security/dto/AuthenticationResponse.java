package com.elderlyCare.api.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private String userId;
    private Long expiration;
}
