package com.elderlyCare.api.registration.dto;

import com.elderlyCare.api.util.EmailValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class RegistrationRequestDTO {
    private String firstName;
    private String lastName;
    @Email(regexp = EmailValidator.EMAIL, message = "Please enter a valid email")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
    private String organizationName;
    private String organizationPanNo;
}
