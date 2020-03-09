package com.kuan.demo.mapper;


import com.kuan.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question")
    List<Question> list();

    @Select("select count(1) from question")
    Integer findCount();

    @Select("select * from question limit #{offset},#{size}")
    List<Question> pagelistfind(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
}
