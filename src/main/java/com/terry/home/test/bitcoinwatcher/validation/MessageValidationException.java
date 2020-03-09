package com.terry.home.test.bitcoinwatcher.validation;

import org.springframework.validation.BindingResult;
import lombok.Getter;

@Getter
public class MessageValidationException extends RuntimeException {

    private BindingResult errors;

    public MessageValidationException(String message, BindingResult errors) {
        super(message);
        this.errors = errors;
    }

}
