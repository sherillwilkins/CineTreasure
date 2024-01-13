package com.w83ll43.domain;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.json.JSONUtil;
import com.w83ll43.domain.entity.Movie;
import com.w83ll43.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SpringBootTest
public class Tests {

    @Autowired
    private MovieService movieService;

    @Test
    public void test() {
        String path = "D:\\Users\\w83ll43\\Desktop\\mac_vod.json";
        HashMap<Integer, String> map = new HashMap<>();
        map.put(13, "喜剧片");
        map.put(14, "动作片");
        map.put(15, "剧情片");
        map.put(16, "战争片");
        map.put(20, "爱情片");
        map.put(21, "科幻片");
        map.put(22, "恐怖片");
        FileReader fileReader = new FileReader(path);
        String string = fileReader.readString();
        List<Vod> vods = JSONUtil.toList(string, Vod.class);
        List<Movie> movies = vods.stream().map(vod -> {
            if (vod.getTypeId1() == 2) {
                Movie movie = new Movie();
                movie.setTitle(vod.getVodName());
                movie.setImage(vod.getVodPic());
                movie.setDescription(vod.getVodBlurb());
                movie.setActor(vod.getVodActor());
                String url = vod.getVodPlayUrl().split("\\$")[1];
                movie.setUrl(url);
                String date = vod.getVodPubdate().split("\\(")[0];
                try {
                    if (!("".equals(date) || date == null) && date.length() > 9) {
                        Date d = DateUtil.parse(date);
                        movie.setYear(d);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                movie.setType(map.get(vod.getTypeId()));
                movie.setGenre(vod.getVodTag());
                movie.setRating(vod.getVodDoubanScore());
                movie.setRatingCount(new Random().nextInt(1000));
                movie.setNeedVip(0);
                movie.setRegion(vod.getVodArea());
                movie.setPopularity(vod.getVodHits());
                movie.setHits(vod.getVodHits());
                movie.setHitsWeek(vod.getVodHitsWeek());
                movie.setHitsMonth(vod.getVodHitsMonth());
                return movie;
            }
            return null;
        }).collect(Collectors.toList());
        movies = movies.stream()
                .filter(movie -> movie != null)
                .filter(movie -> movie.getYear() != null)
                .collect(Collectors.toList());
        movieService.saveBatch(movies);
    }
}
