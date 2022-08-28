package com.careyq.explore.common.util.tree.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 树节点配置类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-28
 */
@Data
@Accessors(chain = true)
public class TreeNodeConfig {

    /**
     * 默认属性的单例对象
     */
    private static TreeNodeConfig DEFAULT_CONFIG = new TreeNodeConfig();

    /**
     * 树节点默认属性
     */
    private String idKey = "value";
    private String nameKey = "label";
    private String childrenKey = "children";
    private String parentIdKey = "parentId";

    public static TreeNodeConfig getDefaultConfig(){
        return DEFAULT_CONFIG;
    }

}
