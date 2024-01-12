package com.w83ll43.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.w83ll43.domain.entity.Movie;
import com.baomidou.mybatisplus.extension.service.IService;
import com.w83ll43.domain.entity.Ratings;
import com.w83ll43.domain.vo.MovieRankRequest;
import com.w83ll43.domain.vo.QueryMovieRequest;

import java.util.List;

/**
* @author w83ll43
* @description 针对表【movie(电影表)】的数据库操作Service
* @createDate 2024-01-10 21:19:15
*/
public interface MovieService extends IService<Movie> {

    List<Movie> getQueryMovieList(QueryMovieRequest request);

    Movie getMovieByMid(Long mid);

    void updateMovie(Movie movie);

    List<Movie> getRecommendMovie();

    Page<Movie> getMovieRanking(MovieRankRequest request);
}
