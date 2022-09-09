package com.careyq.explore.server.dto;

import com.careyq.explore.common.dto.PageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文章筛选 DTO
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ArticlePageDTO extends PageDTO {

    /**
     * 文章标题
     */
    private String title;

    /**
     * 分类 ID
     */
    private Long categoryId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否是原创 0：否 1：是
     */
    private Boolean isOriginal;
}
