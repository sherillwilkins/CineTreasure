package com.w83ll43.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.w83ll43.domain.entity.Movie;
import com.w83ll43.domain.vo.MovieRankRequest;
import com.w83ll43.domain.vo.QueryMovieRequest;
import com.w83ll43.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    private MovieService movieService;

    @RequestMapping("/index")
    public String index(Model model) {
        List<Movie> movies = movieService.getQueryMovieList(QueryMovieRequest.builder().pageNo(1).pageSize(24).build());
        model.addAttribute("movies", movies);
        model.addAttribute("recommendMovies", movieService.getMovieRanking(new MovieRankRequest(1, 4, 4)).getRecords());
//        model.addAttribute("rankMovie1", movieService.getMovieRanking(new MovieRankRequest(1, 9, 1)).getRecords());
//        model.addAttribute("rankMovie2", movieService.getMovieRanking(new MovieRankRequest(1, 9, 2)).getRecords());
        model.addAttribute("rankMovie3", movieService.getMovieRanking(new MovieRankRequest(1, 9, 3)).getRecords());
        return "index";
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/movie")
    public String movie(Model model, @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "12") Integer pageSize) {
        Page<Movie> moviePage = movieService.getMovieList(pageNo, pageSize);
        model.addAttribute("moviePage", moviePage);
        return "movie";
    }

    @RequestMapping("/movie/detail/{mid}")
    public String movieDetail(Model model, @PathVariable("mid") Long mid) {
        Movie movie = movieService.getMovieByMid(mid);
        model.addAttribute("movie", movie);
        return "movie_detail";
    }

    @RequestMapping(value = {"/rank/{type}", "/rank"})
    public String rank(Model model, @PathVariable(value = "type", required = false) Integer type, @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
        if (type == null) type = 1;
        model.addAttribute("type", type);
        model.addAttribute("rankMoviePage", movieService.getMovieRanking(new MovieRankRequest(pageNo, pageSize, type)));
        return "rank";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/feedback")
    public String feedback() {
        return "feedback";
    }
}
