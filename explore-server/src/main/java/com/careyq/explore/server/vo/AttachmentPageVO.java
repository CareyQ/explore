package com.careyq.explore.server.vo;

import com.careyq.explore.common.annotation.FileUrl;
import lombok.Data;

/**
 * <p>
 * 文件分页 VO
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-13
 */
@Data
public class AttachmentPageVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件路径
     */
    @FileUrl
    private String path;

}
