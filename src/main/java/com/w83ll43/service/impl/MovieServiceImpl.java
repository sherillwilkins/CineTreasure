package com.w83ll43.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.constant.RedisConstant;
import com.w83ll43.domain.Averages;
import com.w83ll43.domain.entity.Movie;
import com.w83ll43.domain.entity.Ratings;
import com.w83ll43.domain.vo.MovieRankRequest;
import com.w83ll43.domain.vo.QueryMovieRequest;
import com.w83ll43.service.MovieService;
import com.w83ll43.mapper.MovieMapper;
import com.w83ll43.service.RatingsService;
import com.w83ll43.utils.BaseContext;
import com.w83ll43.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author w83ll43
 * @description 针对表【movie(电影表)】的数据库操作Service实现
 * @createDate 2024-01-10 21:19:15
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie>
        implements MovieService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RatingsService ratingsService;

    @Override
    public List<Movie> getQueryMovieList(QueryMovieRequest request) {
        return getMoviePage(request).getRecords();
    }

    @Override
    public Page<Movie> getQueryMoviePage(QueryMovieRequest request) {
        return getMoviePage(request);
    }

    private Page<Movie> getMoviePage(QueryMovieRequest request) {
        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Page<Movie> page = (Page<Movie>) redisTemplate.opsForValue().get(RedisConstant.getKey(RedisConstant.MOVIE_SEARCH_STRING, servletRequest.getRequestURI() + "?" + servletRequest.getQueryString()));
        if (page != null) {
            return page;
        }
        page = new Page<>(request.getPageNo(), request.getPageSize());
        LambdaQueryWrapper<Movie> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.like(request.getKeyword() != null, Movie::getTitle, request.getKeyword());
        lambdaQueryWrapper.or();
        lambdaQueryWrapper.like(request.getKeyword() != null, Movie::getActor, request.getKeyword());
        if (request.getYear() != null) {
            lambdaQueryWrapper.between(request.getYear() != null, Movie::getYear, DateTimeUtil.getYearFirst(request.getYear()), DateTimeUtil.getYearLast(request.getYear()));
        }
        lambdaQueryWrapper.like(request.getRegion() != null, Movie::getRegion, request.getRegion());
        lambdaQueryWrapper.like(request.getGenre() != null, Movie::getGenre, request.getGenre());
        lambdaQueryWrapper.like(request.getType() != null, Movie::getType, request.getType());
        this.page(page, lambdaQueryWrapper);

        redisTemplate.opsForValue().set(RedisConstant.getKey(RedisConstant.MOVIE_SEARCH_STRING, servletRequest.getRequestURI() + "?" + servletRequest.getQueryString()), page, 60 * 60 * 24, TimeUnit.SECONDS);
        return page;
    }

    @Override
    public Page<Movie> getMovieList(int pageNo, int pageSize) {
        return this.page(new Page<>(pageNo, pageSize));
    }

    @Override
    public Movie getMovieByMid(Long mid) {
        Movie movie = (Movie) redisTemplate.opsForValue().get(RedisConstant.getKey(RedisConstant.MOVIE_INFO_STRING, mid));
        if (movie == null) {
            movie = this.getById(mid);
            redisTemplate.opsForValue().set(RedisConstant.getKey(RedisConstant.MOVIE_INFO_STRING, mid), movie, 60 * 60 * 24, TimeUnit.SECONDS);
        }
        return movie;
    }

    @Override
    public void updateMovie(Movie movie) {
        this.updateById(movie);
        redisTemplate.opsForValue().setIfPresent(RedisConstant.getKey(RedisConstant.MOVIE_INFO_STRING, movie.getMid()), movie, 60 * 60 * 24, TimeUnit.SECONDS);
    }

    @Override
    public List<Movie> getRecommendMovie() {
        Long uid = BaseContext.getCurrentId();
        ratingsService.getUserRatings(uid);
        return null;
    }

    @Override
    public Page<Movie> getMovieRanking(MovieRankRequest request) {
        Page<Movie> page = (Page<Movie>) redisTemplate.opsForValue().get(RedisConstant.getKey(RedisConstant.MOVIE_RANK_SIRING, request.getType(), request.getPageNo(), request.getPageSize()));
        if (!(page == null || page.getRecords().isEmpty())) {
            return page;
        }
        page = new Page<>(request.getPageNo(), request.getPageSize());
        LambdaQueryWrapper<Movie> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        Date startTime;
//        Date endTime = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(endTime);
        switch (request.getType()) {
            case 1:
//                calendar.add(Calendar.DATE, -7);
//                startTime = calendar.getTime();
//                genWrapper(request, startTime, endTime, lambdaQueryWrapper);
                lambdaQueryWrapper.orderByDesc(Movie::getHitsWeek);
                break;
            case 2:
//                calendar.add(Calendar.MONTH, -1);
//                startTime = calendar.getTime();
//                genWrapper(request, startTime, endTime, lambdaQueryWrapper);
                lambdaQueryWrapper.orderByDesc(Movie::getHitsMonth);
                break;
            case 3:
                lambdaQueryWrapper.orderByDesc(Movie::getHits);
                break;
            case 4:
                lambdaQueryWrapper.orderByDesc(Movie::getRating);
                break;
            case 5:
                lambdaQueryWrapper.orderByDesc(Movie::getPopularity);
            default:
                break;
        }
        this.page(page, lambdaQueryWrapper);
        // 排行榜每天更新
        redisTemplate.opsForValue().set(RedisConstant.getKey(RedisConstant.MOVIE_RANK_SIRING, request.getType(), request.getPageNo(), request.getPageSize()), page, 60 * 60 * 24, TimeUnit.SECONDS);
        return page;
    }

    private void genWrapper(MovieRankRequest request, Date startTime, Date endTime, LambdaQueryWrapper<Movie> lambdaQueryWrapper) {
        // 查找本周评分记录
        List<Ratings> ratings = ratingsService.queryRatingRangeTime(startTime, endTime);
        // 根据评分记录计算平均分
        // 选取评分最高的电影 ID
        List<Long> ids = ratings.stream()
                .collect(Collectors.groupingBy(Ratings::getMid, Collectors.summarizingDouble(Ratings::getValue)))
                .entrySet().stream().map(entry -> new Averages(entry.getKey(), entry.getValue().getAverage()))
                .sorted(Comparator.comparing(Averages::getAverage).reversed())
                .limit(request.getPageSize()).map(Averages::getId)
                .collect(Collectors.toList());
        lambdaQueryWrapper.in(Movie::getMid, ids);
        lambdaQueryWrapper.orderByDesc(Movie::getPopularity);
    }
}
