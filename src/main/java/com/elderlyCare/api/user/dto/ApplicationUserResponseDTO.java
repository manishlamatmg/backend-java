package com.elderlyCare.api.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class ApplicationUserResponseDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String district;
    private String localAddress;
    private String permanentAddress;
}
