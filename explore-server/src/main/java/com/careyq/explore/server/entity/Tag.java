package com.careyq.explore.server.entity;

import com.careyq.explore.common.entity.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Tag extends BaseModel<Tag> {

    /**
     * 标签名称
     */
    private String name;

    /**
     * 标签别名，路径标识
     */
    private String alias;


    @Override
    public Serializable pkVal() {
        return null;
    }

}