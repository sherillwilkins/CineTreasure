package com.w83ll43.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 电影表
 * @TableName movie
 */
@TableName(value = "movie")
@Data
public class Movie implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long mid;

    /**
     * 电影名称
     */
    private String title;

    /**
     * 电影海报
     */
    private String image;

    /**
     * 电影简介
     */
    private String description;

    /**
     * 电影主演
     */
    private String actor;

    /**
     * 上映年份
     */
    private Date year;

    /**
     * 地区
     */
    private String region;

    /**
     * 题材
     */
    private String genre;

    /**
     * 类型
     */
    private String type;

    /**
     * 播放地址
     */
    private String url;

    /**
     * 评分
     */
    private Double rating;

    /**
     * 评分人数
     */
    private Integer ratingCount;

    /**
     * 是否需要 VIP
     */
    private Integer needVip;

    /**
     * 影片热度
     */
    private Integer popularity;

    @TableField(exist = false)
    private static final long serialVersionUID = -2566761509830399186L;
}