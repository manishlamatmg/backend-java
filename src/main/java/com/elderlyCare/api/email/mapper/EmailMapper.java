package com.elderlyCare.api.email.mapper;

import com.elderlyCare.api.email.dto.EmailFieldDTO;
import com.elderlyCare.api.registration.dto.RegistrationRequestDTO;
import com.elderlyCare.api.user.entity.ApplicationUser;

public class EmailMapper {

    public static EmailFieldDTO mapperToSendEmail(RegistrationRequestDTO registrationRequestDTO, String token){
        return EmailFieldDTO.builder()
                .firstName(registrationRequestDTO.getFirstName())
                .lastName(registrationRequestDTO.getLastName())
                .email(registrationRequestDTO.getEmail())
                .template("registration")
                .token(token)
                .build();
    }

    public static EmailFieldDTO emailSendToChangePassword(String email, String token){
         return EmailFieldDTO.builder()
                 .email(email)
                 .token(token)
                 .template("reset")
                 .build();
    }

}
