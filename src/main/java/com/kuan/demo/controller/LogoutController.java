package com.kuan.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LogoutController {
    @GetMapping("/logout")
    public String logout(HttpServletRequest res){
        res.getSession().removeAttribute("user");
        return "index";
    }
}
