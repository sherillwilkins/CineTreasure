package com.w83ll43.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 电影评分表
 * @TableName ratings
 */
@TableName(value ="ratings")
@Data
public class Ratings implements Serializable {
    /**
     * 评分 id
     */
    @TableId
    private Long rid;

    /**
     * 评分用户
     */
    private Long uid;

    /**
     * 被评分电影
     */
    private Long mid;

    /**
     * 评分值
     */
    private Long value;

    /**
     * 评分时间
     */
    private Date rtime;

    @TableField(exist = false)
    private static final long serialVersionUID = -2605755785239684906L;
}