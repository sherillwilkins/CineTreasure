package com.w83ll43.controller;

import com.w83ll43.domain.entity.Movie;
import com.w83ll43.domain.vo.QueryMovieRequest;
import com.w83ll43.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    private MovieService movieService;

    @RequestMapping("/index")
    public String index(Model model) {
        QueryMovieRequest request = new QueryMovieRequest();
        request.setPageNo(1);
        request.setPageSize(24);
        List<Movie> movies = movieService.getQueryMovieList(request);
        model.addAttribute("movies", movies);
        return "index";
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/movie")
    public String movie() {
        return "movie";
    }

    @RequestMapping("/movie/detail/{mid}")
    public String movieDetail(Model model, @PathVariable("mid") Long mid) {
        Movie movie = movieService.getMovieByMid(mid);
        model.addAttribute("movie", movie);
        return "movie_detail";
    }

    @RequestMapping("/rank")
    public String rank() {
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
}
