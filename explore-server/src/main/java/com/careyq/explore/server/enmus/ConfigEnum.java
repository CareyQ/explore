package com.careyq.explore.server.enmus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 配置枚举
 *
 * @author careyq
 * @since 2022-09-13
 */
@Getter
@AllArgsConstructor
public enum ConfigEnum {

    /**
     * 操作成功
     */
    DOMAIN(0, "域名配置");

    private final Integer code;
    private final String desc;
}
