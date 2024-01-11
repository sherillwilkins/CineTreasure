package com.w83ll43.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum MovieType {

    ACTION(1, "动作片"),
    ADVENTURE(2, "冒险片"),
    COMEDY(3, "喜剧片"),
    DRAMA(4, "剧情片"),
    FANTASY(5, "奇幻片"),
    HORROR(6, "恐怖片"),
    ROMANCE(7, "爱情片"),
    HISTORICAL(8, "历史片");

    private final Integer type;

    private final String desc;

    private static final Map<Integer, MovieType> cache;

    static {
        cache = Arrays.stream(MovieType.values()).collect(Collectors.toMap(MovieType::getType, Function.identity()));
    }

    public static MovieType of(Integer type) {
        return cache.get(type);
    }
}
