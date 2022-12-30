package com.careyq.explore.server.entity;

import com.careyq.explore.common.entity.BaseModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "配置 code 不能为空")
    private Integer code;

    /**
     * 配置描述
     */
    private String configDesc;

    /**
     * 配置值
     */
    @NotBlank(message = "配置值不能为空")
    private String value;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
