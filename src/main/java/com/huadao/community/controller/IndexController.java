package com.huadao.community.controller;

import com.huadao.community.DTO.PaginationDTO;
import com.huadao.community.mapper.UserMapper;
import com.huadao.community.model.User;
import com.huadao.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name="page",defaultValue = "1")Integer page,
                        @RequestParam(name="size",defaultValue = "10")Integer size){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    User user = userMapper.getUserWithToken(cookie.getValue());
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }

        PaginationDTO pagination = questionService.questionList(page,size);
        model.addAttribute("pagination",pagination);

        return "index";
    }
}
