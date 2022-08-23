package com.careyq.explore.server.enmus;

import com.careyq.explore.common.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 菜单类型枚举
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-21
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum implements IEnum {

    /**
     * 后台菜单
     */
    ADMIN(0, "后台菜单"),
    /**
     * 门户菜单
     */
    WEB(1, "门户菜单");

    private final Integer code;

    private final String desc;
}
