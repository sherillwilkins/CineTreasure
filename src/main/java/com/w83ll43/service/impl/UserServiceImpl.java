package com.w83ll43.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.domain.entity.User;
import com.w83ll43.service.UserService;
import com.w83ll43.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author w83ll43
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2023-12-19 14:32:00
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}




