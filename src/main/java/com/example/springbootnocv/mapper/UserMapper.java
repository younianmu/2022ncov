package com.example.springbootnocv.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootnocv.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where username = #{username} and password = #{password}")
    User login(String username, String password);
}
