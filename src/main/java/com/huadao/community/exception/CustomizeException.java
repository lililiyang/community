package com.huadao.community.exception;

/**
 * @author liyang
 * @date 2020/1/6
 */
public class CustomizeException extends RuntimeException {
    private String message;

    public CustomizeException(CustomeizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage(){
        return message;
    }
}
