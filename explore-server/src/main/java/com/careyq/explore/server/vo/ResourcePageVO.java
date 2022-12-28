package com.careyq.explore.server.vo;

import com.careyq.explore.common.annotation.FileUrl;
import lombok.Data;

/**
 * <p>
 * 资源分页 VO
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-13
 */
@Data
public class ResourcePageVO {

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

    /**
     * 文件类型
     */
    private String type;

    /**
     * 是否是封面
     */
    private Boolean isCover;

}
