package com.w83ll43.constant;

public class RedisConstant {

    public static final String BASE_KEY = "cine:";

    /**
     * 用户信息
     */
    public static final String USER_INFO_STRING = "user:detail:uid_%d";

    /**
     * 电影信息
     */
    public static final String MOVIE_INFO_STRING = "movie:detail:mid_%d";


    /**
     * 电影排行榜
     */
    public static final String MOVIE_RANK_SIRING = "movie:rank:type_%d:no_%d:size:%d";

    /**
     * 电影搜索详情
     * http://localhost:10086/page/movie?pageNo=1&pageSize=12&year=2022&genre=%E6%83%8A%E6%82%9A&type=%E5%8A%A8%E4%BD%9C%E7%89%87&region=%E6%BE%B3%E5%A4%A7%E5%88%A9%E4%BA%9A
     */
    public static final String MOVIE_SEARCH_STRING = "movie:search:request_%s";


    public static String getKey(String key, Object... objects) {
        return BASE_KEY + String.format(key, objects);
    }
}
