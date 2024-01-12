package com.w83ll43.domain.vo;

import lombok.Data;

@Data
public class QueryMovieRequest {

    /**
     * 页码
     */
    Integer pageNo;

    /**
     * 页尺寸
     */
    Integer pageSize;

    /**
     * 关键词
     */
    String keyword;

    /**
     * 电影主演
     */
    String actor;

    /**
     * 年份
     */
    Integer year;

    /**
     * 地区
     */
    String region;

    /**
     * 题材
     */
    Integer genre;

    /**
     * 类型
     */
    Integer type;
}
