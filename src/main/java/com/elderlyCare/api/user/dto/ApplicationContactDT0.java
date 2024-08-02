package com.elderlyCare.api.user.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class ApplicationContactDT0 {
    private String phoneNumber;
    private String permanentAddress;
    private String localAddress;
    private String district;
    private String userName;
    private String email;
    private String message;
}
