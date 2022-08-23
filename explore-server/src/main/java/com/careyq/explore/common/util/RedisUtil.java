package com.careyq.explore.common.util;

import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Redis 工具类
 *
 * @author careyq
 * @since 2022-08-21
 */
@Component
@AllArgsConstructor
public class RedisUtil {

    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1L;
    /**
     * 默认过期时长，单位：秒
     */
    public static final long DEFAULT_EXPIRE = 60L * 60L * 24L;

    private static RedisUtil instance;
    private final StringRedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
        instance = this;
    }

    /**
     * 设置缓存
     *
     * @param key   键
     * @param value 值
     */
    public static void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存
     *
     * @param key    键
     * @param value  值
     * @param expire 过期时间
     */
    public static void set(String key, Object value, long expire) {
        set(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存
     *
     * @param key    键
     * @param value  值
     * @param expire 过期时间
     * @param unit   时间单位
     */
    public static void set(String key, Object value, long expire, TimeUnit unit) {
        instance.redisTemplate.opsForValue().set(key, JSON.toJSONString(value));
        instance.redisTemplate.expire(key, expire, unit);
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public static String get(String key) {
        return instance.redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取缓存，不设置过期时间
     *
     * @param key   键
     * @param clazz 转换的类
     * @param <T>   要转换的类
     * @return 实体
     */
    public static <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    /**
     * 获取缓存，可设置过期时间
     *
     * @param key    键
     * @param clazz  转换的类
     * @param expire 过期时间，可选
     * @param <T>    要转换的类
     * @return 实体
     */
    public static <T> T get(String key, Class<T> clazz, long expire) {
        String value = instance.redisTemplate.opsForValue().get(key);
        if (expire != NOT_EXPIRE) {
            instance.redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : JSON.parseObject(value, clazz);
    }

}
