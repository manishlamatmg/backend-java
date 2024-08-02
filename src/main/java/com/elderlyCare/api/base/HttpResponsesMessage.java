package com.elderlyCare.api.base;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.http.HttpStatus;

import java.util.Map;
@Data
@SuperBuilder(toBuilder = true)
@Jacksonized
public class HttpResponsesMessage {

    protected String timeStamp;
    protected Integer id ;
    protected HttpStatus status;
    protected String message;
    protected String developerMessage;
    protected String requestMethod;
    protected Map<?, ?> data;
}
