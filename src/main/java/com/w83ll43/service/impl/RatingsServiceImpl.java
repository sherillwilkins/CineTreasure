package com.w83ll43.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.domain.entity.Ratings;
import com.w83ll43.service.RatingsService;
import com.w83ll43.mapper.RatingsMapper;
import org.springframework.stereotype.Service;

/**
 * @author w83ll43
 * @description 针对表【ratings(电影评分表)】的数据库操作Service实现
 * @createDate 2024-01-10 21:19:15
 */
@Service
public class RatingsServiceImpl extends ServiceImpl<RatingsMapper, Ratings>
        implements RatingsService {

    @Override
    public Ratings queryRatingByMid(Long uid, Long mid) {
        return lambdaQuery().eq(Ratings::getUid, uid).eq(Ratings::getMid, mid).one();
    }
}




