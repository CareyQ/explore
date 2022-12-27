package com.careyq.explore.server.vo;

import com.careyq.explore.common.annotation.FileUrl;
import lombok.Data;

/**
 * <p>
 * 资源分类 VO
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-13
 */
@Data
public class ResourceCategoryPageVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 是否加密
     */
    private Boolean isEncrypt;

    /**
     * 是否展示
     */
    private Boolean isShow;

    /**
     * 封面资源 ID
     */
    @FileUrl
    private String cover;
}
