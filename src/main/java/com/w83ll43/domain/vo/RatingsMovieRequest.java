package com.w83ll43.domain.vo;

import lombok.Data;

@Data
public class RatingsMovieRequest {

    private Long mid;

    private Long score;

    private String content;
}
