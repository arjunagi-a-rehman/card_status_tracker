package com.example.cardStatusTracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message,String resource,String identifier){
        super(String.format("resource of %s, %s, %s",message,resource,identifier));
    }
}