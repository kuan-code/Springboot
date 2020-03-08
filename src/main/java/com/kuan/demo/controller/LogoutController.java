package com.kuan.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class LogoutController {
    @GetMapping("/logout")
    public String logout(HttpServletRequest res,
                         HttpServletResponse resp){
        HttpSession session = res.getSession();
        session.removeAttribute("user");
        Cookie[] cookies = res.getCookies();
        if (cookies !=null && cookies.length>0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                    break;
                }
            }
        }
        return "redirect:/";
    }
}
