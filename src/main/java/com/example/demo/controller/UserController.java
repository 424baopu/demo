package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liushuangyu
 * @Date: 2022/10/25 18:04
 * @Description:
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("queryUser")
    public User queryUserById(@RequestParam(value = "id") Integer id) {
        return userMapper.queryUserById(id);
    }


}
