package com.careyq.explore.common.util.tree.parser;

import com.careyq.explore.common.util.tree.entity.TreeNodeMap;

/**
 * <p>
 * 树节点解析器
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-28
 */
@FunctionalInterface
public interface NodeParser<T> {

    /**
     * 源数据转换为树节点
     *
     * @param object   源数据实体
     * @param treeNode 树节点实体
     */
    void parse(T object, TreeNodeMap treeNode);
}
