package com.huadao.community.controller;

import com.huadao.community.DTO.AccessTokenDTO;
import com.huadao.community.DTO.GitHubUser;
import com.huadao.community.mapper.UserMapper;
import com.huadao.community.model.User;
import com.huadao.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author liyang
 * @date 2019/11/21 22:38
 */
@Controller
@RequestMapping("/account")
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${github.redirect_url}")
    private String redirect_url;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/github/login")
    public void githubLogin(HttpServletResponse response) throws IOException {
        String githubState = "adgasgdsdhgi";
        response.sendRedirect("https://github.com/login/oauth/authorize?client_id=" + client_id +
                "&redirect_uri=" + redirect_url + "&state=" + githubState);

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

    @GetMapping("/github/callback")
    public String githubCallback(@RequestParam(name = "code", required = false) String code,
                                 @RequestParam(name = "state", required = false) String state,
                                 HttpServletResponse response
                                 ) {

        System.out.println("==>state:" + state);
        System.out.println("==>code:" + code);
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_url(redirect_url);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        System.out.println("access_token ===== " + accessToken);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);
        if (gitHubUser != null && gitHubUser.getId() != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(gitHubUser.getName());
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(gitHubUser.getAvatar_url());
            userMapper.insertUser(user);
            Cookie cookie = new Cookie("token",token);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return "redirect:/";
    }


}
