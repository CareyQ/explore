package com.careyq.explore.common.enums;

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
}
