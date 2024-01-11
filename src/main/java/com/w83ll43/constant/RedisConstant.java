package com.w83ll43.constant;

public class RedisConstant {

    public static final String BASE_KEY = "cine:";

    /**
     * 用户信息
     */
    public static final String USER_INFO_STRING = "user:uid_%d";


    public static String getKey(String key, Object... objects) {
        return BASE_KEY + String.format(key, objects);
    }
}
