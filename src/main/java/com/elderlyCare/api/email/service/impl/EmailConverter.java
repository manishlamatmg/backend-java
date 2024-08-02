package com.elderlyCare.api.email.service.impl;

import com.elderlyCare.api.email.dto.EmailDTO;
import com.elderlyCare.api.email.dto.EmailFieldDTO;

import java.util.HashMap;
import java.util.Map;

public class EmailConverter {

    public static EmailDTO sendEmailToRegister(EmailFieldDTO emailFieldDTO){
        Map<String, Object> properties = new HashMap<>();
        properties.put("firstName", emailFieldDTO.getFirstName());
        properties.put("lastName", emailFieldDTO.getLastName());
        properties.put("template", emailFieldDTO.getTemplate());
        properties.put("token", emailFieldDTO.getToken());
        return EmailDTO.builder()
                .toAddress(emailFieldDTO.getEmail())
                .templateName(emailFieldDTO.getTemplate())
                .properties(properties)
                .build();
    }

    public static EmailDTO sendEmailToChangePassword(EmailFieldDTO emailFieldDTO){
        Map<String, Object> properties = new HashMap<>();
        properties.put("token", emailFieldDTO.getToken());
        properties.put("template", emailFieldDTO.getTemplate());
        return EmailDTO.builder()
                .toAddress(emailFieldDTO.getEmail())
                .templateName(emailFieldDTO.getTemplate())
                .properties(properties)
                .build();
    }
}
