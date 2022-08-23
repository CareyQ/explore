package com.careyq.explore.server.entity;

import com.careyq.explore.common.entity.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 配置表
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Config extends BaseModel<Config> {

    /**
     * 配置 code
     */
    private Integer code;

    /**
     * 配置描述
     */
    private String configDesc;

    /**
     * 配置值
     */
    private String value;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
