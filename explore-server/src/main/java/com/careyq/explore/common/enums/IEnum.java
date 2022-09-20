package com.careyq.explore.common.enums;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * <p>
 * 枚举通用接口
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-21
 */
public interface IEnum {

    /**
     * 获取枚举值
     *
     * @return 枚举值
     */
    Integer getCode();

    /**
     * 获取枚举描述
     *
     * @return 枚举描述
     */
    String getDesc();

    /**
     * 通过 code 获取枚举
     *
     * @param enumClass 枚举类
     * @param code      code 值
     * @param <T>       IEnum 子类
     * @return 枚举
     */
    static <T extends IEnum> T codeOf(Class<T> enumClass, Integer code) {
        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException(enumClass.getName() + "不是枚举类");
        }
        return Stream.of(enumClass.getEnumConstants())
                .filter(v -> Objects.equals(v.getCode(), code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("未找到该枚举类型"));
    }

    /**
     * 检查枚举是否无此 code
     *
     * @param enumClass 枚举类
     * @param code      code 值
     * @return 是否存在
     */
    static boolean noCode(Class<? extends IEnum> enumClass, Integer code) {
        return Stream.of(enumClass.getEnumConstants()).noneMatch(e -> Objects.equals(e.getCode(), code));
    }
}
