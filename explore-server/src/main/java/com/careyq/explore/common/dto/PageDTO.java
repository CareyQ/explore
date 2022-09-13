package com.careyq.explore.common.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 分页 DTO
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-09
 */
@Data
@Accessors(chain = true)
public class PageDTO {

    /**
     * 当前页
     */
    @NotNull(message = "当前页不能为空")
    private Integer current;

    /**
     * 页面大小
     */
    @NotNull(message = "页面大小不能为空")
    private Integer size;
}
