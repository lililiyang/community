package com.huadao.community.DTO;

import com.huadao.community.exception.CustomeizeErrorCode;
import com.huadao.community.exception.CustomizeException;
import lombok.Data;

/**
 * @author liyang
 * @date 2020/4/28
 */
@Data
public class ResultDTO {

    private Integer code;
    private String message;

    public static ResultDTO errorOf(String message,Integer code){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomeizeErrorCode errorCode){
        return ResultDTO.errorOf(errorCode.getMessage(),errorCode.getCode());
    }

    public static ResultDTO errorOf(CustomizeException e){
        return ResultDTO.errorOf(e.getMessage(),e.getCode());
    }

    public static ResultDTO okOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }
}
