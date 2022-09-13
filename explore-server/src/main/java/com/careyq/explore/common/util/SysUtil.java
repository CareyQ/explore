package com.careyq.explore.common.util;

import com.careyq.explore.server.enmus.ConfigEnum;
import com.careyq.explore.server.service.ConfigService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 系统工具类
 *
 * @author careyq
 * @since 2022-09-13
 */
@Component
@AllArgsConstructor
public class SysUtil {

    private final ConfigService configService;
    private static SysUtil instance;

    private static final String HTTP_STR = "http";

    @PostConstruct
    public void init() {
        instance = this;
    }

    /**
     * 获取文件地址，如果参数带有 "http" 则直接返回
     */
    public static String getFileUrl(String path) {
        if (StrUtil.isBlank(path) || path.contains(HTTP_STR)) {
            return path;
        }
        return instance.configService.getConfigValue(ConfigEnum.DOMAIN.getCode()) + StrUtil.SLASH + path;
    }
}
