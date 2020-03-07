package com.kuan.demo.mapper;

import com.kuan.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modified,bio,headimg) values(#{account_id}," +
            "#{name},#{token},#{gmt_create},#{gmt_modified},#{bio},#{headimg})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User checkToken(@Param("token") String value);

}
