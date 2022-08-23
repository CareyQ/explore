package com.careyq.explore.server.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 菜单 VO
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Data
@Accessors(chain = true)
public class MenuVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 是否外链
     */
    private Boolean isBlank;

    /**
     * 路由地址
     */
    private String route;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 子菜单
     */
    private List<MenuVO> children;

    public void addChildren(MenuVO children) {
        if (Objects.isNull(this.children)) {
            this.children = new ArrayList<>();
        }
        this.children.add(children);
    }

}
