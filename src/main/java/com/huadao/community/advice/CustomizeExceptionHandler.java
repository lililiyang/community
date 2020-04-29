package com.huadao.community.advice;

import com.alibaba.fastjson.JSON;
import com.huadao.community.DTO.ResultDTO;
import com.huadao.community.exception.CustomizeException;
import com.huadao.community.exception.QuestionErrorCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author liyang
 * @date 2020/1/6
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
        String contentType = request.getContentType();
        ResultDTO resultDTO;
        if("application/json".equals(contentType)){
            //返回JSON
            if (e instanceof CustomizeException) {
                resultDTO = ResultDTO.errorOf((CustomizeException) e);
            } else {
                resultDTO = ResultDTO.errorOf(QuestionErrorCode.SYS_ERROR);
            }

            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ex) {
            }

            return null;
        }else{
            //错误页面跳转
            if (e instanceof CustomizeException) {
                model.addAttribute("code", ((CustomizeException) e).getCode());
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", QuestionErrorCode.SYS_ERROR.getMessage());
            }

            return new ModelAndView("error");
        }
    }


}
