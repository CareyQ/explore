package com.careyq.explore.server.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文件分类 VO
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-13
 */
@Data
@Accessors(chain = true)
public class AttachmentCategoryVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 文件分类名称
     */
    private String name;

    /**
     * 分类描述
     */
    private String categoryDesc;

    /**
     * 私密分类密码
     */
    private String password;

    /**
     * 是否展示
     */
    private Boolean isShow;
}
