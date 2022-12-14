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
     * 域名配置
     */
    DOMAIN(0, "域名配置"),
    /**
     * 默认资源文件夹
     */
    DEFAULT_RESOURCE_CATEGORY(1, "默认资源文件夹 ID"),
    /**
     * 资源服务域名
     */
    RESOURCE_DOMAIN(2, "资源服务域名"),
    /**
     * 资源存储路径
     */
    RESOURCE_PATH(3, "资源存储路径");

    private final Integer code;
    private final String desc;
}
