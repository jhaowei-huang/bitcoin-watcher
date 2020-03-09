package com.terry.home.test.bitcoinwatcher.dto;

import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.stomp.StompCommand;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponse extends AbstractResponse {

    private Serializable response;

    public MessageResponse(String destination, Message message) {
        this.setStatus(true);
        this.setHttpStatus(HttpStatus.OK.value());
        this.setRequestUrl(destination);
        this.setMethod(StompCommand.SEND.name());
        this.setResponse(message);
    }

}
