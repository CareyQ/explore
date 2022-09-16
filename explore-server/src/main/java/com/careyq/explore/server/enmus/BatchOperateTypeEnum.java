package com.careyq.explore.server.enmus;

import com.careyq.explore.common.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 批量类型枚举
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-21
 */
@Getter
@AllArgsConstructor
public enum BatchOperateTypeEnum implements IEnum {

    /**
     * 批量删除
     */
    DEL(0, "批量删除"),
    /**
     * 批量移动
     */
    MOVE(1, "批量移动");

    private final Integer code;

    private final String desc;
}
