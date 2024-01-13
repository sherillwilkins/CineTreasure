package com.w83ll43.domain.vo;

import lombok.Data;

@Data
public class UserVo {

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;
}
