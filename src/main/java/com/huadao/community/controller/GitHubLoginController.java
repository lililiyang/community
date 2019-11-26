package com.huadao.community.controller;

import com.huadao.community.DTO.AccessTokenDTO;
import com.huadao.community.DTO.GitHubUser;
import com.huadao.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liyang
 * @version 1.0
 * @date 2019/11/21 22:38
 */
@Controller
@RequestMapping("/account/github")
public class GitHubLoginController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @GetMapping("/login")
    public void githubLogin(HttpServletResponse response) throws IOException {
        String githubState = "adgasgdsdhgi";
        response.sendRedirect("https://github.com/login/oauth/authorize?client_id=babac79062f199612a48" +
                "&redirect_uri=http://localhost:8080/account/github/callback&state="+ githubState);

        /**
         * Spring MVC项目中页面重定向一般使用return "redirect:/other/controller/";即可。
         *
         * 而Spring Boot当我们使用了@RestController注解，上述写法只能返回字符串，解决方法如下：
         *
         * 将一个HttpServletResponse参数添加到处理程序方法然后调用 response.sendRedirect("some-url");
         */
       // return "redirect:https://github.com/login/oauth/authorize?client_id=babac79062f199612a48" +
      //  "&redirect_uri=http://localhost:8080/account/github/callback&state="+ githubState);
    }

    @GetMapping("/callback")
    @ResponseBody
    public void githubCallback(@RequestParam(name="code",required = false)String code,
                               @RequestParam(name="state",required = false)String state) {

        System.out.println("==>state:" + state);
        System.out.println("==>code:" + code);
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("babac79062f199612a48");
        accessTokenDTO.setClient_secret("d733c4820ab0308261a866efd0bf3e6ac4dce472");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_url("http://localhost:8080/account/github/callback");
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        System.out.println("access_token ===== "+accessToken);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);
        System.out.println(gitHubUser.getName());

    }

}
