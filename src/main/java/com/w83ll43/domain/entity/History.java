package com.w83ll43.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 观看历史表
 * @TableName history
 */
@TableName(value ="history")
@Data
public class History implements Serializable {
    /**
     * 观看记录 ID
     */
    @TableId
    private Long hid;

    /**
     * 用户 ID
     */
    private Long uid;

    /**
     * 电影 ID
     */
    private Long mid;

    /**
     * 播放时间
     */
    private Date htime;

    /**
     * 观看时间（看了多少）
     */
    private String duration;

    @TableField(exist = false)
    private static final long serialVersionUID = -5123602786918489935L;
}