package com.kuan.demo.controller;

import com.kuan.demo.dto.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register_submit(@RequestParam(name = "username") String username,
                                  @RequestParam(name = "email") String email,
                                  @RequestParam(name = "password") String password ){
        RegisterUser registerUser = new RegisterUser();
        registerUser.setUsername(username);
        registerUser.setEmail(email);
        registerUser.setPassword(password);
        System.out.println(registerUser.toString());
        return "redirect:/";
    }
    @GetMapping("/register_success")
    public String register_success(){
        return "registerSuccess";
    }
}
