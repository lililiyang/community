package com.huadao.community.exception;

/**
 * @author liyang
 * @date 2020/1/6
 */
public enum QuestionErrorCode implements CustomeizeErrorCode {


    QUESTION_NOT_FOUND(201, "你找到问题不在了，要不要换个试试？");

    private String message;
    private Integer code;

    QuestionErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public Integer getCode() {
        return null;
    }
}
