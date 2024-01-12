package com.w83ll43.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.w83ll43.common.Result;
import com.w83ll43.domain.entity.History;
import com.w83ll43.domain.entity.Movie;
import com.w83ll43.domain.entity.User;
import com.w83ll43.domain.enums.Role;
import com.w83ll43.domain.vo.MovieRankRequest;
import com.w83ll43.domain.vo.QueryMovieRequest;
import com.w83ll43.service.HistoryService;
import com.w83ll43.service.MovieService;
import com.w83ll43.service.UserService;
import com.w83ll43.utils.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private UserService userService;

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
    public Result<Page<Movie>> getMovieList(int pageNo, int pageSize) {
        Page<Movie> page = new Page<>(pageNo, pageSize);
        movieService.page(page);
        return Result.success(page);
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
    @GetMapping("/watch/{mid}")
    public Result<Movie> watchMovie(@PathVariable("mid") Long mid) {
        Long uid = BaseContext.getCurrentId();
        Movie movie = movieService.getMovieByMid(mid);
        if (movie.getNeedVip() == 1) {
            User user = userService.getUserByUid(uid);
            if (Role.of(user.getRole()) != Role.VIPER) {
                return Result.error("请先开通 VIP");
            }
        }
        History history = new History();
        history.setUid(uid);
        history.setMid(mid);
        history.setHtime(new Date());
        historyService.save(history);
        return Result.success(movie);
    }

    /**
     * 获取推荐的电影
     * @return
     */
    @GetMapping("/recommend")
    public Result<List<Movie>> getRecommendMovie() {
        List<Movie> movies = movieService.getRecommendMovie();
        return Result.success(movies);
    }

    /**
     * 获取电影排行榜
     * @param request
     * @return
     */
    @PostMapping("/rank")
    public Result<Page<Movie>> getMovieRanking(@RequestBody MovieRankRequest request) {
        Page<Movie> page = movieService.getMovieRanking(request);
        return Result.success(page);
    }
}
