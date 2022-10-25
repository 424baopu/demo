package com.example.demo.mapper;

import com.example.demo.mapper.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: liushuangyu
 * @Date: 2022/10/25 17:54
 * @Description:
 */
@Mapper
public interface UserMapper {
    User queryUserById(Integer id);
}
