package com.w83ll43.domain.vo;

import lombok.Data;

@Data
public class MovieRankRequest {

    private Integer pageNo;

    private Integer pageSize;

    /**
     * 排行类型
     * 1-周排行 2-月排行 3-总评分排行 4-热播排行
     */
    private Integer type;
}
