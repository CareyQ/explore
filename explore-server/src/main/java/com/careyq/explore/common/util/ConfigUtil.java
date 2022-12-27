package com.careyq.explore.common.util;

import com.careyq.explore.server.enmus.ConfigEnum;
import com.careyq.explore.server.service.ConfigService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 系统工具类
 *
 * @author careyq
 * @since 2022-09-13
 */
@Component
@AllArgsConstructor
public class ConfigUtil {

    private final ConfigService configService;
    private static ConfigUtil instance;

    @PostConstruct
    public void init() {
        instance = this;
    }

    /**
     * 获取配置
     */
    public static String getConfig(Integer code) {
        if (code == null) {
            return StrUtil.EMPTY;
        }
        return instance.configService.getConfigValue(code);
    }

    /**
     * 获取默认资源分类 ID
     *
     * @return ID
     */
    public static Long getResourceCategory() {
        String config = getConfig(ConfigEnum.DEFAULT_RESOURCE_CATEGORY.getCode());
        if (StrUtil.isNotBlank(config)) {
            return Long.valueOf(config);
        }
        return null;
    }
}
