package com.careyq.explore.server.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 文章分页 VO
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-09
 */
@Data
public class ArticlePageVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 文章别名，路径标识
     */
    private String alias;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 分类
     */
    private String categoryName;

    /**
     * 是否置顶 0：否 1：是
     */
    private Boolean isTop;

    /**
     * 是否是原创 0：否 1：是
     */
    private Boolean isOriginal;

    /**
     * 封面图
     */
    private String thumbnail;

    /**
     * 标签名称
     */
    private String tags;

    /**
     * 标签名称
     */
    private List<String> tagsName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 文章浏览量
     */
    private Long hits;

    /**
     * 文章点赞数
     */
    private Long likes;
}
