package com.careyq.explore.server.entity;

import com.careyq.explore.common.entity.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "分类名称不能为空")
    private String name;

    /**
     * 分类别名，路径标识
     */
    @NotBlank(message = "分类别名不能为空")
    private String alias;

    /**
     * 浏览量
     */
    private Long hits;

    /**
     * 排序
     */
    private Integer sort;


    @Override
    public Serializable pkVal() {
        return this.getId();
    }

}
