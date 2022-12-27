package com.careyq.explore.server.entity;

import com.careyq.explore.common.entity.BaseModel;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 博客分类/标签表
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PostMeta extends BaseModel<PostMeta> {

    /**
     * 类型 0：分类 1：文章标签 2：片刻标签
     */
    private Integer type;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 别名，路径标识
     */
    @NotBlank(message = "别名不能为空")
    private String slug;


    @Override
    public Serializable pkVal() {
        return this.getId();
    }

}
