package com.w83ll43.controller;

import com.w83ll43.annotation.Authorization;
import com.w83ll43.common.Result;
import com.w83ll43.domain.dto.UserLoginDto;
import com.w83ll43.domain.dto.UserRegisterDto;
import com.w83ll43.domain.entity.User;
import com.w83ll43.domain.enums.Role;
import com.w83ll43.service.UserService;
import com.w83ll43.utils.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/aop")
    @Authorization(value = Role.USER, message = "message")
    public Result<String> aop() {
        return Result.success("success");
    }

    /**
     * 会员注册
     * @return
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserRegisterDto userRegisterDto) {
        userService.register(userRegisterDto);
        return Result.success("注册成功！");
    }

    /**
     * 会员登录
     * @return
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody UserLoginDto userLoginDto, HttpSession session) {
        User user = userService.login(userLoginDto);
        if (user == null) {
            return Result.error("用户邮箱或密码错误！");
        }
        Long uid = user.getUid();
        session.setAttribute("user", uid);
        redisTemplate.opsForValue().set("uid:" + uid, user);
        return Result.success("登录成功！");
    }

    /**
     * 用户登出
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success("success");
    }

    /**
     * 开通会员
     */
    @PostMapping("/vip")
    public Result<String> openVip(@RequestBody String email) {
        userService.openVip(email);
        return Result.success("开通会员成功！");
    }
}
