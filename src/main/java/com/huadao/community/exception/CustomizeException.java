package com.huadao.community.exception;

/**
 * @author liyang
 * @date 2020/1/6
 */
public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;

    public CustomizeException(CustomeizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
    }

    @Override
    public String getMessage(){
        return message;
    }

    public Integer getCode(){
        return code;
    }
}
