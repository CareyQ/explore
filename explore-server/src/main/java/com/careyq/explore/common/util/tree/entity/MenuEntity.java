package com.careyq.explore.common.util.tree.entity;

/**
 * <p>
 *
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-28
 */
public class MenuEntity {
    // 菜单ID
    private Long id;

    // 上级菜单ID
    private Long pid;

    // 菜单名称
    private String name;

    // 菜单编码
    private String code;

    // 菜单图标
    private String icon;

    // 菜单链接
    private String url;

    public MenuEntity() {
    }

    public MenuEntity(Long id, Long pid, String name, String code,String url) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.code = code;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
