package com.terry.home.test.bitcoinwatcher.validation;

import lombok.Getter;

@Getter
public class ParameterValidationException extends RuntimeException {

    private String parameterName;

    public ParameterValidationException(String parameterName, String message) {
        super(message);
        this.parameterName = parameterName;
    }

}
