package com.w83ll43.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.w83ll43.domain.entity.Feedback;
import com.w83ll43.domain.entity.Movie;
import com.w83ll43.domain.vo.MovieRankRequest;
import com.w83ll43.domain.vo.QueryMovieRequest;
import com.w83ll43.service.FeedbackService;
import com.w83ll43.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping("/index")
    public String index(Model model) {
        List<Movie> movies = movieService.getQueryMovieList(QueryMovieRequest.builder().pageNo(1).pageSize(24).build());
        model.addAttribute("movies", movies);
        model.addAttribute("recommendMovies", movieService.getMovieRanking(new MovieRankRequest(1, 4, 5)).getRecords());
        model.addAttribute("rankMovie1", movieService.getMovieRanking(new MovieRankRequest(1, 9, 1)).getRecords());
        model.addAttribute("rankMovie2", movieService.getMovieRanking(new MovieRankRequest(1, 9, 2)).getRecords());
        model.addAttribute("rankMovie3", movieService.getMovieRanking(new MovieRankRequest(1, 9, 3)).getRecords());
        return "index";
    }

    @RequestMapping("/movie")
    public String movie(Model model, @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "12") Integer pageSize, String type, String genre, String region, Integer year) {
        Page<Movie> moviePage = movieService.getQueryMoviePage(QueryMovieRequest.builder().type(type).genre(genre).region(region).year(year).pageNo(pageNo).pageSize(pageSize).build());
        model.addAttribute("moviePage", moviePage);
        HashMap<String, String> hashMap = new HashMap<>();
        String s = type != null ? hashMap.put("type", type) : hashMap.put("type", "全部");
        s = region != null ? hashMap.put("region", region) : hashMap.put("region", "全部");
        s = year != null ? hashMap.put("year", String.valueOf(year)) : hashMap.put("year", "全部");
        s = genre != null ? hashMap.put("genre", genre) : hashMap.put("genre", "全部");
        model.addAttribute("obj", hashMap);
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
    public String feedback(Model model, @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "5") Integer pageSize) {
        model.addAttribute("feedbackPage", feedbackService.getFeedbackList(pageNo, pageSize));
        return "feedback";
    }


    @RequestMapping("/watch/{mid}")
    public String watch(@PathVariable("mid") Long mid, Model model) {
        Movie movie = movieService.getMovieByMid(mid);
        model.addAttribute("movie", movie);
        model.addAttribute("recommendMovies", movieService.getMovieRanking(new MovieRankRequest(1, 4, 4)).getRecords());
        return "watch";
    }

    @RequestMapping("/search")
    public String search(Model model, @RequestParam String keyword) {
        model.addAttribute("movies", movieService.getQueryMovieList(QueryMovieRequest.builder().pageNo(1).pageSize(10).keyword(keyword).build()));
        return "search";
    }
}
