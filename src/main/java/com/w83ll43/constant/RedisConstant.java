package com.w83ll43.constant;

public class RedisConstant {

    public static final String BASE_KEY = "cine:";

    /**
     * 用户信息
     */
    public static final String USER_INFO_STRING = "user:uid_%d";

    /**
     * 电影信息
     */
    public static final String MOVIE_INFO_STRING = "movie:mid_%d";


    /**
     * 电影排行榜
     */
    public static final String MOVIE_RANK_SIRING = "movie:rank_%d";


    public static String getKey(String key, Object... objects) {
        return BASE_KEY + String.format(key, objects);
    }
}
