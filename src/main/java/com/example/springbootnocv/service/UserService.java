package com.example.springbootnocv.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootnocv.entity.User;

public interface UserService extends IService<User> {

    User login(String username, String password);
}
