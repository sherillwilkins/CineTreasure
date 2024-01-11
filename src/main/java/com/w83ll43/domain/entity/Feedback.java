package com.w83ll43.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户反馈表
 * @TableName feedback
 */
@TableName(value ="feedback")
@Data
public class Feedback implements Serializable {
    /**
     * 反馈 ID
     */
    @TableId
    private Long fid;

    /**
     * 反馈用户
     */
    private Long uid;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 反馈时间戳
     */
    private Date ftime;

    @TableField(exist = false)
    private static final long serialVersionUID = 3056837552001584489L;
}