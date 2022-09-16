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
     * @param enumType 枚举类
     * @param code     code 值
     * @param <T>      IEnum 子类
     * @return 枚举
     */
    static <T extends IEnum> T codeOf(Class<T> enumType, Integer code) {
        if (!enumType.isEnum()) {
            throw new IllegalArgumentException(enumType.getName() + "不是枚举类");
        }
        return Stream.of(enumType.getEnumConstants())
                .filter(v -> Objects.equals(v.getCode(), code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("未找到该枚举类型"));
    }
}
