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
public enum BlogTypeEnum implements IEnum {

    /**
     * 文章
     */
    ARTICLE(0, "文章"),
    /**
     * 片刻
     */
    MOMENT(1, "片刻");

    private final Integer code;

    private final String desc;
}
