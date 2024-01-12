package com.w83ll43.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.constant.RedisConstant;
import com.w83ll43.domain.dto.UserLoginDto;
import com.w83ll43.domain.dto.UserRegisterDto;
import com.w83ll43.domain.entity.User;
import com.w83ll43.domain.enums.Role;
import com.w83ll43.service.UserService;
import com.w83ll43.mapper.UserMapper;
import com.w83ll43.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author w83ll43
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2023-12-19 14:32:00
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void register(UserRegisterDto userRegisterDto) {
        User user = new User();
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(MD5Util.md5(userRegisterDto.getPassword()));
        this.save(user);
    }

    @Override
    public User login(UserLoginDto userLoginDto) {
        User user = this.getUserByEmail(userLoginDto.getEmail());
        if (user != null && user.getPassword().equals(MD5Util.md5(userLoginDto.getPassword()))) return user;
        else return null;
    }

    @Override
    public void openVip(Long uid) {
        User user = this.getById(uid);
        user.setRole(Role.VIPER.getType());
        this.updateById(user);
    }

    @Override
    public User getUserByUid(Long uid) {
        User user = (User) redisTemplate.opsForValue().get(RedisConstant.getKey(RedisConstant.USER_INFO_STRING, uid));
        if (user == null) {
            user = this.getById(uid);
            redisTemplate.opsForValue().set(RedisConstant.getKey(RedisConstant.USER_INFO_STRING, uid), user, 60 * 60, TimeUnit.SECONDS);
        }
        return user;
    }


    private User getUserByEmail(String email) {
        return lambdaQuery().eq(User::getEmail, email).one();
    }
}




