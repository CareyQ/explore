package com.careyq.explore.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回结果状态
 *
 * @author careyq
 * @since 2022-08-19
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    /**
     * 操作成功
     */
    SUCCESS(0, "操作成功"),
    /**
     * 操作失败
     */
    FAIL(5000, "操作失败"),
    /**
     * 未登录
     */
    PARAMS_ERROR(4000, "参数有误"),
    /**
     * 未登录
     */
    UN_LOGIN(4003, "用户未登录");

    private final Integer code;
    private final String desc;
}
