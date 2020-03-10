package com.terry.home.test.bitcoinwatcher.controller;

import com.terry.home.test.bitcoinwatcher.dto.ErrorResponse;
import com.terry.home.test.bitcoinwatcher.dto.Message;
import com.terry.home.test.bitcoinwatcher.dto.MessageResponse;
import com.terry.home.test.bitcoinwatcher.validation.MessageValidation;
import com.terry.home.test.bitcoinwatcher.validation.MessageValidationException;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class ChatController {

    @CrossOrigin
    @MessageValidation
    @MessageMapping(value = {"/chat", "/chat/"})
    @SendTo("/topic/chat")
    public MessageResponse chat(Message message, StompHeaderAccessor headerAccessor) {
        return new MessageResponse(headerAccessor.getDestination(), message);
    }

    @MessageExceptionHandler(MessageValidationException.class)
    @SendToUser("/topic/chat")
    public ErrorResponse handleMessagingException(MessageValidationException e, StompHeaderAccessor headerAccessor) {
        return new ErrorResponse(headerAccessor.getDestination(), e.getErrors().getGlobalErrors());
    }

}
