package com.elderlyCare.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ElderlyCareException extends  RuntimeException{
    private final HttpStatus status;
     public ElderlyCareException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
}
