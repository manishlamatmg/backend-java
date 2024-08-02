package com.elderlyCare.api.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class PasswordDTO {
    private String token;
    private String password;
}
