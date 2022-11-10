package com.careyq.explore.server.enmus;

import com.careyq.explore.common.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 博客类型枚举
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-21
 */
@Getter
@AllArgsConstructor
public enum PostMetaTypeEnum implements IEnum {

    /**
     * 文章
     */
    CATEGORY(0, "分类"),
    /**
     * 片刻
     */
    ARTICLE_TAG(1, "文章标签"),
    /**
     * 片刻
     */
    MOMENT_TAG(2, "片刻标签");

    private final Integer code;

    private final String desc;
}
