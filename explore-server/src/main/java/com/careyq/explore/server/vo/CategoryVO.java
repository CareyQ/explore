package com.careyq.explore.server.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 分类 VO
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-27
 */
@Data
@Accessors(chain = true)
public class CategoryVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类别名
     */
    private String alias;

    /**
     * 浏览量
     */
    private Long hits;

    /**
     * 文章数
     */
    private Long count;

    /**
     * 排序
     */
    private Integer sort;

}
