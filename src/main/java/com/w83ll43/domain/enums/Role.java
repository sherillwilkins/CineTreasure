package com.w83ll43.domain.enums;

import lombok.*;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum Role {

    ADMINISTRATOR(-1, "管理员"),
    VISTOR(0, "游客"),
    USER(1, "普通用户"),
    VIPER(2, "VIP用户");

    private final Integer type;

    private final String desc;

    private static final Map<Integer, Role> cache;

    static {
        cache = Arrays.stream(Role.values()).collect(Collectors.toMap(Role::getType, Function.identity()));
    }

    public static Role of(Integer type) {
        return cache.get(type);
    }
}
