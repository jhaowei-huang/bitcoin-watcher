package com.terry.home.test.bitcoinwatcher.dto;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.validation.ObjectError;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse extends AbstractResponse {

    private Map<String, String> response;

    public ErrorResponse(String destination, Collection<ObjectError> errors) {
        this.setStatus(false);
        this.setHttpStatus(HttpStatus.PRECONDITION_FAILED.value());
        this.setRequestUrl(destination);
        this.setMethod(StompCommand.MESSAGE.name());
        Map<String, String> map = new HashMap<>();
        for (ObjectError error : errors) {
            map.put(error.getCode(), error.getDefaultMessage());
        }
        this.setResponse(map);
    }

    public ErrorResponse(String url, Map<String, String> response) {
        this.setStatus(false);
        this.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        this.setRequestUrl(url);
        this.setMethod(HttpMethod.GET.name());
        this.setResponse(response);
    }

}
