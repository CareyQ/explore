package com.careyq.explore.common.util.tree;

import com.alibaba.fastjson2.JSON;
import com.careyq.explore.common.util.tree.entity.MenuEntity;
import com.careyq.explore.common.util.tree.entity.TreeNodeConfig;
import com.careyq.explore.common.util.tree.entity.TreeNodeMap;
import com.careyq.explore.common.util.tree.parser.NodeParser;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 树形工具类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-28
 */
@UtilityClass
public class TreeUtil {

    /**
     * 树构建
     */
    public static <T> List<TreeNodeMap> build(List<T> list, Object parentId, NodeParser<T> nodeParser) {
        return build(list, parentId, TreeNodeConfig.getDefaultConfig(), nodeParser);
    }

    /**
     * 构建树
     *
     * @param list           待构建的列表
     * @param parentId       父节点 ID
     * @param treeNodeConfig 树配置
     * @param nodeParser     Convert
     * @param <T>            泛型
     * @return 结果
     */
    public static <T> List<TreeNodeMap> build(List<T> list, Object parentId, TreeNodeConfig treeNodeConfig, NodeParser<T> nodeParser) {
        List<TreeNodeMap> treeNodes = new ArrayList<>();
        for (T obj : list) {
            TreeNodeMap treeNode = new TreeNodeMap(treeNodeConfig);
            nodeParser.parse(obj, treeNode);
            treeNodes.add(treeNode);
        }

        List<TreeNodeMap> finalTreeNodes = new ArrayList<>();
        for (TreeNodeMap treeNode : treeNodes) {
            if (Objects.equals(parentId, treeNode.getParentId())) {
                finalTreeNodes.add(treeNode);
                innerBuild(treeNodes, treeNode);
            }
        }
        return finalTreeNodes;
    }

    /**
     * 构建子树
     *
     * @param treeNodes  子节点列表
     * @param parentNode 父节点
     */
    private static void innerBuild(List<TreeNodeMap> treeNodes, TreeNodeMap parentNode) {
        for (TreeNodeMap childNode : treeNodes) {
            if (parentNode.getId().equals(childNode.getParentId())) {
                List<TreeNodeMap> children = parentNode.getChildren();
                if (children == null) {
                    children = new ArrayList<>();
                    parentNode.setChildren(children);
                }
                children.add(childNode);
                childNode.setParentId(parentNode.getId());
                innerBuild(treeNodes, childNode);
            }
        }
    }

    public static void main(String[] args) {
        List<MenuEntity> menuEntityList = new ArrayList<>();
        menuEntityList.add(new MenuEntity(1L, 0L, "系统管理", "sys", "/sys"));
        menuEntityList.add(new MenuEntity(11L, 1L, "用户管理", "user", "/sys/user"));
        menuEntityList.add(new MenuEntity(111L, 11L, "用户添加", "userAdd", "/sys/user/add"));
        menuEntityList.add(new MenuEntity(2L, 0L, "店铺管理", "store", "/store"));
        menuEntityList.add(new MenuEntity(21L, 2L, "商品管理", "shop", "/shop"));

        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名
        List<TreeNodeMap> result = build(menuEntityList, 0L, (entity, tree) -> {
            tree.setId(entity.getId());
            tree.setParentId(entity.getPid());
            tree.setName(entity.getName());
            // 属性扩展
            tree.extra("extra1", "123");
        });

        System.out.println(JSON.toJSONString(result));


    }
}
