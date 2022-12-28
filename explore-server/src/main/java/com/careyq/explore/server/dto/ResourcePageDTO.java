package com.careyq.explore.server.dto;

import com.careyq.explore.common.dto.PageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资源分页筛选 DTO
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResourcePageDTO extends PageDTO {

    /**
     * 文件分类 ID
     */
    private Long categoryId;

    /**
     * 文件类型
     */
    private String type;

}
