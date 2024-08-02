package com.elderlyCare.api.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApplicationUserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
