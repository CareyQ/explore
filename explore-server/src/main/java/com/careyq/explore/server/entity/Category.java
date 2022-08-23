package com.careyq.explore.server.entity;

import com.careyq.explore.common.entity.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 分类表
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseModel<Category> {

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类别名，路径标识
     */
    private String alias;

    /**
     * 父级 ID
     */
    private Long parentId;

    /**
     * 排序
     */
    private Integer sort;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
