package com.careyq.explore.server.entity;

import com.careyq.explore.common.entity.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseModel<Menu> {

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 路由地址
     */
    private String router;

    /**
     * 路由组件
     */
    private String component;

    /**
     * 是否外链
     */
    private Boolean isBlank;

    /**
     * 菜单类型 0：后台，1：前台
     */
    private Integer type;

    /**
     * 父级 ID
     */
    private Long parentId;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单排序，值越小越靠前
     */
    private Integer sort;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
