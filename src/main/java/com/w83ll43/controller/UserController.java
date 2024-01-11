package com.w83ll43.controller;

import com.w83ll43.annotation.Authorization;
import com.w83ll43.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/aop")
    @Authorization(value = "1", message = "message")
    public Result<String> aop() {
        return Result.success("success");
    }
}
