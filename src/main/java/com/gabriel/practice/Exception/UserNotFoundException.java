package com.gabriel.practice.Exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException{

    private final String messageKey;
    private final Object[] args;
    private final ErrorCode errorCode;

    public UserNotFoundException(String messageKey, ErrorCode errorCode, Object... args) {
        super(messageKey);
        this.messageKey = messageKey;
        this.errorCode = errorCode;
        this.args = args;
    }

}
