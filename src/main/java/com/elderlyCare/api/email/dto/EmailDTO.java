package com.elderlyCare.api.email.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Getter
@Setter
@Jacksonized
@Builder
public class EmailDTO {

    private String toAddress;
    private String templateName;
    private Map<String, Object> properties;


}
