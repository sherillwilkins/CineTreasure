package com.w83ll43.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.domain.entity.Ratings;
import com.w83ll43.domain.entity.User;
import com.w83ll43.domain.vo.RatingsVo;
import com.w83ll43.service.RatingsService;
import com.w83ll43.mapper.RatingsMapper;
import com.w83ll43.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author w83ll43
 * @description 针对表【ratings(电影评分表)】的数据库操作Service实现
 * @createDate 2024-01-10 21:19:15
 */
@Service
public class RatingsServiceImpl extends ServiceImpl<RatingsMapper, Ratings>
        implements RatingsService {

    @Autowired
    private UserService userService;

    @Override
    public Ratings queryRatingByMid(Long uid, Long mid) {
        return lambdaQuery().eq(Ratings::getUid, uid).eq(Ratings::getMid, mid).one();
    }

    @Override
    public List<Ratings> getUserRatings(Long uid) {
        return null;
    }

    @Override
    public List<Ratings> queryRatingRangeTime(Date startTime, Date endTime) {
        return lambdaQuery().between(Ratings::getRtime, startTime, endTime).list();
    }

    @Override
    public List<RatingsVo> getMovieRatings(Long mid) {
        List<Ratings> ratings = lambdaQuery().eq(Ratings::getMid, mid).list();
        return ratings.stream().map(rating -> {
            User user = userService.getUserByUid(rating.getUid());
            return RatingsVo.builder()
                    .avatar(user.getAvatar())
                    .nickname(user.getNickname())
                    .content(rating.getContent())
                    .rtime(rating.getRtime()).build();
        }).collect(Collectors.toList());
    }
}




