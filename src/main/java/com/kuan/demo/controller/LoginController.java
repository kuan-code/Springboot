package com.kuan.demo.controller;

import com.kuan.demo.dto.AStoken_Dto;
import com.kuan.demo.dto.GithubUser;
import com.kuan.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping("/callback")
    public String AS_token(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AStoken_Dto aStoken_dto = new AStoken_Dto();
        aStoken_dto.setClient_id(cilentId);
        aStoken_dto.setClient_secret(cilentSecret);
        aStoken_dto.setCode(code);
        aStoken_dto.setState(state);
        aStoken_dto.setRedirect_url(cilentRedirect);
        String asToken = githubProvider.getAsToken(aStoken_dto);
        GithubUser user = githubProvider.getUser(asToken);
        System.out.println(user.getName());
        return "index";

    }
}
