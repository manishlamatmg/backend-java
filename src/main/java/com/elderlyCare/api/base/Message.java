package com.elderlyCare.api.base;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import org.springframework.web.service.annotation.GetExchange;

@Getter
@Setter
@Jacksonized
@Builder
public class Message {
    String message;
}
