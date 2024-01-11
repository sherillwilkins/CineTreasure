package com.w83ll43.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @TableName user
 */
@Data
@TableName(value ="user")
public class User implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long uid;

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

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户角色 1-普通用户、2-会员用户
     */
    private Integer role;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 用户状态 0-禁用 1-正常 2-已删除
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = -3662327538642095592L;
}