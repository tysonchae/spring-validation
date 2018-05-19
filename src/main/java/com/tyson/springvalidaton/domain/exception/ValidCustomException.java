package com.tyson.springvalidaton.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidCustomException {
    private Error[] errors;

    public ValidCustomException(String defaultMessage, String field){
        this.errors = new Error[]{new Error(defaultMessage, field)};
    }
}
