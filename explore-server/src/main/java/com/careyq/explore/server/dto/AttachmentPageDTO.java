package com.careyq.explore.server.dto;

import com.careyq.explore.common.dto.PageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文章 DTO
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AttachmentPageDTO extends PageDTO {

    /**
     * 文件分类 ID
     */
    private Long categoryId;

    /**
     * 文件类型
     */
    private String type;

}
