package com.w83ll43.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRankRequest {

    private Integer pageNo;

    private Integer pageSize;

    /**
     * 排行类型
     * 1-周排行 2-月排行 3-全部排行 4-总评分排行 5-热播排行
     */
    private Integer type;
}
