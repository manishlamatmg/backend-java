package com.elderlyCare.api.email.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@Jacksonized
public class EmailFieldDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String template;
    private String token ;
    private String companyName;
    private ZonedDateTime startingDate;
    private ZonedDateTime endingDate;
}
