package com.w83ll43.service;

import com.w83ll43.domain.entity.Ratings;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author w83ll43
 * @description 针对表【ratings(电影评分表)】的数据库操作Service
 * @createDate 2024-01-10 21:19:15
 */
public interface RatingsService extends IService<Ratings> {

    Ratings queryRatingByMid(Long uid, Long mid);
}
