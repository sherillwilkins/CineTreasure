package com.w83ll43.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.domain.entity.Movie;
import com.w83ll43.service.MovieService;
import com.w83ll43.mapper.MovieMapper;
import org.springframework.stereotype.Service;

/**
* @author w83ll43
* @description 针对表【movie(电影表)】的数据库操作Service实现
* @createDate 2024-01-10 21:19:15
*/
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie>
    implements MovieService{

}




