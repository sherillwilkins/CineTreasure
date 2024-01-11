package com.w83ll43.service;

import com.w83ll43.domain.dto.UserLoginDto;
import com.w83ll43.domain.dto.UserRegisterDto;
import com.w83ll43.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author w83ll43
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-12-19 14:32:00
*/
public interface UserService extends IService<User> {

    void register(UserRegisterDto userRegisterDto);

    User login(UserLoginDto userLoginDto);

    void openVip(String email);
}
