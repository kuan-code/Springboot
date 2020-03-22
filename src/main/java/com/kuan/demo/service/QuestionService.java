package com.kuan.demo.service;

import com.kuan.demo.dto.QuestionDto;
import com.kuan.demo.dto.paginationDto;
import com.kuan.demo.mapper.QuestionMapper;
import com.kuan.demo.mapper.UserMapper;
import com.kuan.demo.model.Question;
import com.kuan.demo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;


    public List<QuestionDto> findDes() {
        List<Question> questionlist = questionMapper.list();
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionlist) {
            User user = userMapper.findByAcountID(String.valueOf(question.getCreator()));
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }

    public paginationDto pageDes(Integer page, Integer size) {
        paginationDto paginationDto = new paginationDto();
        Integer questionCount = questionMapper.findCount();
        paginationDto.autoPageProfile(questionCount,page,size);

        if(page<1){
            page=1;
        }

        if(page>paginationDto.getPageCount()) {
            page = paginationDto.getPageCount();
        }
        Integer offset = size*(page-1);
        List<Question> questionlist = questionMapper.pagelistfind(offset,size);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionlist) {
            User user = userMapper.findByAcountID(String.valueOf(question.getCreator()));
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);

        return paginationDto;
    }
}
