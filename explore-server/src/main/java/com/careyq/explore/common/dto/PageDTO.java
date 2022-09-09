package com.careyq.explore.common.dto;

import lombok.Data;
import lombok.experimental.Accessors;

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
    private Integer current;

    /**
     * 页面大小
     */
    private Integer size;
}
