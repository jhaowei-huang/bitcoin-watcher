package com.terry.home.test.bitcoinwatcher.validation;

import com.terry.home.test.bitcoinwatcher.dto.Message;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

@Aspect
@Component
public class MessageAop {

    @Pointcut("@annotation(com.terry.home.test.bitcoinwatcher.validation.MessageValidation)")
    public void validate() {}

    @Before(value = "validate()")
    private Object doBefore(JoinPoint joinPoint) throws MessageValidationException {
        Message message = (Message) joinPoint.getArgs()[0];
        BindingResult errors = new BeanPropertyBindingResult(message, "message");

        if (StringUtils.isEmpty(message.getUuid())) {
            errors.reject("uuid", "uuid 不可以為空白");
        }
        if (StringUtils.isEmpty(message.getCode())) {
            errors.reject("code", "大頭貼code不可以為空白");
        }
        if (StringUtils.isEmpty(message.getColor())) {
            errors.reject("color", "顏色不可以為空白");
        }
        if (StringUtils.isEmpty(message.getName())) {
            errors.reject("name", "名字不可以為空白");
        }
        if (StringUtils.isEmpty(message.getContent())) {
            errors.reject("content", "訊息內容不可以為空白");
        }
        if (message.getContent().length() > 60) {
            errors.reject("content", "訊息字數須介於1~60字");
        }

        if (errors.hasErrors()) {
            throw new MessageValidationException("傳送訊息錯誤", errors);
        }

        return joinPoint.getArgs();
    }

}
