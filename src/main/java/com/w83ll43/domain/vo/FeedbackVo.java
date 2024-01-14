package com.w83ll43.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class FeedbackVo {

    private Long uid;

    private String nickname;

    private String content;

    private Date ftime;

    private String avatar;
}
