package com.w83ll43.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.w83ll43.common.Result;
import com.w83ll43.domain.entity.History;
import com.w83ll43.domain.entity.Movie;
import com.w83ll43.domain.vo.QueryMovieRequest;
import com.w83ll43.service.HistoryService;
import com.w83ll43.service.MovieService;
import com.w83ll43.utils.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private HistoryService historyService;

    /**
     * 根据电影 id 获取电影
     * @param id 电影 id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result<Movie> getMovieById(@PathVariable("id") Long id) {
        Movie movie = movieService.getById(id);
        return Result.success(movie);
    }


    /**
     * 分页获取电影列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public Result<List<Movie>> getMovieList(int pageNo, int pageSize) {
        Page<Movie> page = new Page<>(pageNo, pageSize);
        movieService.page(page);
        List<Movie> movies = page.getRecords();
        return Result.success(movies);
    }

    /**
     * 根据条件分页查询电影列表
     * @param request
     * @return
     */
    @PostMapping("/query")
    public Result<List<Movie>> queryMovie(@RequestBody QueryMovieRequest request) {
        List<Movie> movies = movieService.getQueryMovieList(request);
        return Result.success(movies);
    }

    /**
     * 观看电影
     * @param mid
     * @return
     */
    @PostMapping("/watch")
    public Result<Movie> watchMovie(@RequestBody Long mid) {
        Movie movie = movieService.getById(mid);
        // TODO 判断是否拥有权限观看
        Long uid = BaseContext.getCurrentId();
        History history = new History();
        history.setUid(uid);
        history.setMid(mid);
        history.setHtime(new Date());
        historyService.save(history);
        return Result.success(movie);
    }


}
