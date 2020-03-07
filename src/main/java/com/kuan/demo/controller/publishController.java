package com.kuan.demo.controller;


import com.kuan.demo.mapper.QuestionMapper;
import com.kuan.demo.mapper.UserMapper;
import com.kuan.demo.model.Question;
import com.kuan.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class publishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublih(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest res,
            Model model){
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        HttpSession session = res.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null){
            question.setCreator(Integer.valueOf(user.getAccount_id()));
            question.setGmt_create(System.currentTimeMillis());
            question.setGmt_modified(question.getGmt_create());
            questionMapper.create(question);
            System.out.println(user.getName()+"发起问题成功，数据库question数据表成功添加一条数据"+question.toString());
            return "redirect:/";
        }
        else{
            model.addAttribute("error","用户未登陆,请登录后再试");
            return "publish";
        }
    }
}
