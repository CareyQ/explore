package com.careyq.explore.server.dto;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 附件批量操作 DTO
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-16
 */
@Data
public class AttachmentBatchOperateDTO {

    /**
     * 批量操作 ID
     */
    @NotEmpty(message = "请选择要操作的数据")
    private List<Long> ids;

    /**
     * 目标分类 ID
     */
    private Long targetCategoryId;

    /**
     * 操作类型
     */
    @NotNull(message = "操作类型不能为空")
    private Integer operationType;

}
