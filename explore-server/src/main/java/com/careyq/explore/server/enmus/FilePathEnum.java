package com.careyq.explore.server.enmus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 文件路径枚举
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-24
 */
@Getter
@AllArgsConstructor
public enum FilePathEnum {

    /**
     * 头像路径
     */
    AVATAR(0, "avatar/", "头像路径"),
    /**
     * 文章附件路径
     */
    ARTICLE(1, "article/", "文章附件路径"),
    /**
     * 其他附件路径
     */
    RESOURCE(2, "resource/", "资源文件路径");

    private final Integer code;

    private final String path;

    private final String desc;
}
