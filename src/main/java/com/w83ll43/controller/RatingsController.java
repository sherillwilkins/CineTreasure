package com.w83ll43.controller;

import com.w83ll43.common.Result;
import com.w83ll43.domain.entity.Movie;
import com.w83ll43.domain.entity.Ratings;
import com.w83ll43.domain.vo.RatingsMovieRequest;
import com.w83ll43.service.MovieService;
import com.w83ll43.service.RatingsService;
import com.w83ll43.utils.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/ratings")
public class RatingsController {

    @Autowired
    private RatingsService ratingsService;

    @Autowired
    private MovieService movieService;

    /**
     * 评分
     * @return
     */
    @PostMapping
//    @Transactional
    public Result<String> rating(@RequestBody RatingsMovieRequest request) {
        Long uid = BaseContext.getCurrentId();

        Ratings ratings = ratingsService.queryRatingByMid(uid, request.getMid());
        if (ratings != null) {
            return Result.error("您已经评过分了！");
        }

        ratings = new Ratings();
        ratings.setUid(uid);
        ratings.setMid(request.getMid());
        ratings.setValue(request.getScore());
        ratings.setContent(request.getContent());
        ratings.setRtime(new Date());
        ratingsService.save(ratings);

//        Movie movie = movieService.getMovieByMid(request.getMid());
//        movie.setRating(((movie.getRating() * movie.getRatingCount()) + request.getScore()) / (movie.getRatingCount() + 1));
//        movieService.updateMovie(movie);
        return Result.success("评分成功！");
    }
}
