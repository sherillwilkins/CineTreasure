package com.w83ll43.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.domain.entity.Movie;
import com.w83ll43.domain.vo.QueryMovieRequest;
import com.w83ll43.service.MovieService;
import com.w83ll43.mapper.MovieMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author w83ll43
* @description 针对表【movie(电影表)】的数据库操作Service实现
* @createDate 2024-01-10 21:19:15
*/
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie>
    implements MovieService{

    @Override
    public List<Movie> getQueryMovieList(QueryMovieRequest request) {
        Page<Movie> page = new Page<>(request.getPageNo(), request.getPageSize());
        LambdaQueryWrapper<Movie> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.like(request.getKeyword() != null, Movie::getTitle, request.getKeyword());
        lambdaQueryWrapper.eq(request.getYear() != null, Movie::getYear, request.getYear());
        lambdaQueryWrapper.eq(request.getRegion() != null, Movie::getRegion, request.getRegion());
        lambdaQueryWrapper.eq(request.getGenre() != null, Movie::getGenre, request.getGenre());
        lambdaQueryWrapper.eq(request.getType()!= null, Movie::getType, request.getType());

        return this.page(page, lambdaQueryWrapper).getRecords();
    }
}




