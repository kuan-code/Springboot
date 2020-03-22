package com.kuan.demo.controller;

import com.kuan.demo.dto.AStoken_Dto;
import com.kuan.demo.dto.GithubUser;
import com.kuan.demo.mapper.UserMapper;
import com.kuan.demo.model.User;
import com.kuan.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.ID}")
    private String cilentId;

    @Value("${github.cilent.Secret}")
    private String cilentSecret;

    @Value("${github.cilent.Redirect}")
    private String cilentRedirect;

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/callback")
    public String AS_token(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest req,
                           HttpServletResponse resp) {
        AStoken_Dto aStoken_dto = new AStoken_Dto();
        aStoken_dto.setClient_id(cilentId);
        aStoken_dto.setClient_secret(cilentSecret);
        aStoken_dto.setCode(code);
        aStoken_dto.setState(state);
        aStoken_dto.setRedirect_url(cilentRedirect);
        String asToken = githubProvider.getAsToken(aStoken_dto);
        System.out.println(asToken);
        GithubUser githubuser = githubProvider.getUser(asToken);
        if (githubuser != null){
            //登录成功
            User user = new User();
            user.setHeadimg(githubuser.getAvatar_url());
            user.setAccount_id(String.valueOf(githubuser.getId()));
            user.setName(githubuser.getName());
            user.setBio(githubuser.getBio());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.insert(user);
            resp.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else{
            //登陆失败
            return "redirect:/";
        }
    }
}
