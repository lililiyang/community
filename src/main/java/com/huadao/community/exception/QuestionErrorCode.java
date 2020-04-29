package com.huadao.community.exception;

/**
 * @author liyang
 * @date 2020/1/6
 */
public enum QuestionErrorCode implements CustomeizeErrorCode {


    QUESTION_NOT_FOUND(2001, "你找到问题不在了，要不要换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或者评论进行回复"),
    USER_NOT_LOGIN(2003,"当前操作需要登录,请先登录后重试!"),
    SYS_ERROR(2004,"服务器冒烟了,要不稍等一下再来试试!!!"),
    TYPE_PARAM_WORNG(2005,"评论类型错误货不存在"),
    ;

    private Integer code;
    private String message;

    QuestionErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
