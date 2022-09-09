package com.careyq.explore.server.enmus;

import com.careyq.explore.common.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 文章状态枚举
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-21
 */
@Getter
@AllArgsConstructor
public enum ArticleStatusEnum implements IEnum {

    /**
     * 草稿
     */
    DRAFT(0, "草稿"),
    /**
     * 已发布
     */
    PUBLISHED(1, "已发布");

    private final Integer code;

    private final String desc;
}
