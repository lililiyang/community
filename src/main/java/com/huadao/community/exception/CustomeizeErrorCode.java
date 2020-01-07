package com.huadao.community.exception;

/**
 * @author liyang
 * @date 2020/1/6
 * 所有业务错误信息的接口
 */
public interface CustomeizeErrorCode {
    /**
     * 返回错误信息
     * @return
     */
    String getMessage();

    /**
     * 返回错误代码
     * @return
     */
    Integer getCode();
}
