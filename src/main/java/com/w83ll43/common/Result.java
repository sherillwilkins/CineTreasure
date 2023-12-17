package com.w83ll43.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果类
 * @param <T>
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class Result<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 动态数据
     */
    private Map<String, Object> map = new HashMap<>();

    /**
     * 成功
     * @param data 返回数据
     * @param <T>  返回数据类型
     * @return 成功的结果
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = 1;
        result.data = data;
        return result;
    }

    public static <T> Result<T> success(String message) {
        Result<T> result = new Result<>();
        result.code = 1;
        result.message = message;
        return result;
    }

    /**
     * 失败
     * @param message 错误消息
     * @param <T>     响应携带的数据类型
     * @return 失败的结果
     */
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.code = 0;
        result.message = message;
        return result;
    }

    /**
     * 失败
     * @param code    错误码
     * @param message 错误消息
     * @param <T>     数据类型
     * @return 失败的结果
     */
    public static <T> Result<T> error(int code, String message) {
        Result<T> result = new Result<>();
        result.code = code;
        result.message = message;
        return result;
    }

    /**
     * 向动态数据添加数据
     * @param key   键
     * @param value 值
     * @return 调用者本身
     */
    public Result<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
