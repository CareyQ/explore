package com.careyq.explore.server.entity;

import com.careyq.explore.common.entity.BaseModel;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 文件分类表
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-13
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ResourceCategory extends BaseModel<ResourceCategory> {

    /**
     * 文件分类名称
     */
    @NotBlank(message = "资源分类名称不能为空")
    private String name;

    /**
     * 分类描述
     */
    private String categoryDesc;

    /**
     * 私密分类密码
     */
    private String password;

    /**
     * 是否展示
     */
    private Boolean isShow;

    /**
     * 封面资源 ID
     */
    private Long cover;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
