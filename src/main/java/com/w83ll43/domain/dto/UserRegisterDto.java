package com.w83ll43.domain.dto;

import lombok.Data;

@Data
public class  UserRegisterDto {

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户密码
     */
    private String password;
}
