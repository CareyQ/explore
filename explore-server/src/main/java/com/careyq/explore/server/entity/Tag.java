package com.careyq.explore.server.entity;

import com.careyq.explore.common.entity.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 标签表
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Tag extends BaseModel<Tag> {

    /**
     * 标签名称
     */
    @NotBlank(message = "标签名称不能为空")
    private String name;

    /**
     * 标签别名，路径标识
     */
    @NotBlank(message = "标签别名不能为空")
    private String alias;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
