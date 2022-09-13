package com.careyq.explore.server.vo;

import lombok.Data;

/**
 * <p>
 * 文件分类 VO
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-13
 */
@Data
public class AttachmentCategoryVO {

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
}
