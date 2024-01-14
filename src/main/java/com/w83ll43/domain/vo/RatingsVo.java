package com.w83ll43.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingsVo {

    private String avatar;

    private String nickname;

    private String content;

    private Date rtime;
}
