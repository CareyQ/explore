package com.careyq.explore.server.dto;

import lombok.Data;

import java.util.List;

/**
 * <p>
 * 片刻 DTO
 * </p>
 *
 * @author CareyQ
 * @since 2022-11-09
 */
@Data
public class MomentDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 附件 ID
     */
    private String attachmentIds;

    /**
     * 标签
     */
    private List<Object> tags;
}
