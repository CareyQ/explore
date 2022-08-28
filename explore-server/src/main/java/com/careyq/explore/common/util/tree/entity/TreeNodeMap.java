package com.careyq.explore.common.util.tree.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 树节点类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-28
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TreeNodeMap extends HashMap<String, Object> {

    private TreeNodeConfig treeNodeConfig;

    @SuppressWarnings("unchecked")
    public <T> T getId() {
        return (T) super.get(treeNodeConfig.getIdKey());
    }

    public <T> void setId(T id) {
        super.put(treeNodeConfig.getIdKey(), id);
    }

    @SuppressWarnings("unchecked")
    public <T> T getParentId() {
        return (T) super.get(treeNodeConfig.getParentIdKey());
    }

    public <T> void setParentId(T parentId) {
        super.put(treeNodeConfig.getParentIdKey(), parentId);
    }

    @SuppressWarnings("unchecked")
    public <T> T getName() {
        return (T) super.get(treeNodeConfig.getNameKey());
    }

    public void setName(String name) {
        super.put(treeNodeConfig.getNameKey(), name);
    }

    @SuppressWarnings("unchecked")
    public List<TreeNodeMap> getChildren() {
        return (List<TreeNodeMap>) super.get(treeNodeConfig.getChildrenKey());
    }

    public void setChildren(List<TreeNodeMap> children) {
        super.put(treeNodeConfig.getChildrenKey(), children);
    }

    public void extra(String key, Object value) {
        super.put(key, value);
    }

}
