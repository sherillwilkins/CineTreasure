package com.w83ll43.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum MovieGenre {
    FAMILY(1, "家庭片"),
    ART(2, "文艺片"),
    MUSICAL(3, "音乐片"),
    CARTOONS(4, "动漫片"),
    WESTERN(5, "普通用户"),
    PERIOD(6, "古装片"),
    CRIME(7, "犯罪片"),
    SUSPENSE(8, "悬疑片"),
    DOCUMENTARY(9, "记录片"),
    WAR(10, "战争片"),
    SPORT(11, "体育片");

    private final Integer type;

    private final String desc;

    private static final Map<Integer, MovieGenre> cache;

    static {
        cache = Arrays.stream(MovieGenre.values()).collect(Collectors.toMap(MovieGenre::getType, Function.identity()));
    }

    public static MovieGenre of(Integer type) {
        return cache.get(type);
    }
}
