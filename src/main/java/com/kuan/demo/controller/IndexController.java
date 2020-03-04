package com.kuan.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kuan.demo.mapper.UserMapper;
import com.kuan.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest res) {
        Cookie[] cookies = res.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String value = cookie.getValue();
                    User user = userMapper.checkToken(value);
                    if (user != null) {
                        res.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        } else {
            return "index";
        }

        return "index";
    }

    @RequestMapping(value = "/getHeadImg", method = RequestMethod.GET)
    @ResponseBody
    public String getHeadImg(HttpServletRequest res) {
        Cookie[] cookies = res.getCookies();
//        System.out.println(cookies);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String value = cookie.getValue();
                    User user = userMapper.checkToken(value);
                    if (user != null) {
                        return user.getHeadimg();
                    }
                    break;
                }
            }
        } else {
            return null;
        }
        return null;
    }
}

