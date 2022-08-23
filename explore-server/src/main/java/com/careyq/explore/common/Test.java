package com.careyq.explore.common;

import com.alibaba.fastjson2.JSON;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-21
 */
public class Test {
    static class Node {
        String code;
        String parentCode;

        List<Node> children;

        public Node() {
            super();
        }

        public Node(String code, String parentCode) {
            super();
            this.code = code;
            this.parentCode = parentCode;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getParentCode() {
            return parentCode;
        }

        public void setParentCode(String parentCode) {
            this.parentCode = parentCode;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void setChildren(List<Node> children) {
            this.children = children;
        }

        public void addChildren(Node child) {
            if (children == null) {
                children = new ArrayList<Node>();
            }
            children.add(child);
        }
    }

    public static void main(String[] args) {
        List<Node> initList = initTreeData();
        Map<String, Node> root = new LinkedHashMap<String, Node>();
        for (Node item : initList) {
            Node parent = root.get(item.getParentCode());
            if (parent == null) {
                parent = new Node();
                root.put(item.getParentCode(), parent);
            }
            root.put(item.getCode(), item);
            parent.addChildren(item);
        }
        Node tree = root.get("0");
        System.out.println(JSON.toJSON(tree));
    }

    static List<Node> initTreeData() {
        List<Node> initList = new ArrayList<>();
        initList.add(new Node("001", "0"));
        initList.add(new Node("001001", "001"));
        initList.add(new Node("001002", "001"));
        initList.add(new Node("002", "0"));
        initList.add(new Node("002001", "002"));
        initList.add(new Node("002002", "002"));
        initList.add(new Node("002002001", "002002"));
        initList.add(new Node("002002002", "002002"));
        initList.add(new Node("003", "0"));
        initList.add(new Node("003001", "003"));
        initList.add(new Node("003002", "003"));
        initList.add(new Node("003002001", "003002"));
        initList.add(new Node("003002002", "003002"));
        initList.add(new Node("003002003", "003002"));
        initList.add(new Node("003002004", "003002"));
        initList.add(new Node("003002005", "003002"));
        initList.add(new Node("003002006", "003002"));
        initList.add(new Node("005", "0"));
        initList.add(new Node("005001", "005"));
        initList.add(new Node("005001002", "005001"));
        initList.add(new Node("005001002001", "005001002"));
        initList.add(new Node("005001002002", "005001002"));
        return initList;
    }
}
