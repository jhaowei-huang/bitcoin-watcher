package com.terry.home.test.bitcoinwatcher.validation;

import lombok.Getter;

@Getter
public class CurrentSourceInvalidException extends RuntimeException {

    private int sourceId;

    public CurrentSourceInvalidException(int sourceId, String message) {
        super(message);
        this.sourceId = sourceId;
    }

}
