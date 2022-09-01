package com.careyq.explore.server.dto;

import com.careyq.explore.server.annotation.ArticlePublishValid;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 文章 DTO
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-31
 */
@Data
public class ArticleDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 文章别名，路径标识
     */
    @NotNull(message = "文章别名不能为空", groups = {ArticlePublishValid.class})
    private String alias;

    /**
     * 文章标题
     */
    @NotNull(message = "文章标题不能为空")
    private String title;

    /**
     * 分类 ID
     */
    @NotNull(message = "文章分类不能为空", groups = {ArticlePublishValid.class})
    private Long categoryId;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 允许评论 0：禁止 1：允许
     */
    @NotNull(message = "是否评论不能为空", groups = {ArticlePublishValid.class})
    private Boolean allowComment;

    /**
     * 是否置顶 0：否 1：是
     */
    @NotNull(message = "是否置顶不能为空", groups = {ArticlePublishValid.class})
    private Boolean isTop;

    /**
     * 是否是原创 0：否 1：是
     */
    @NotNull(message = "是否原创不能为空", groups = {ArticlePublishValid.class})
    private Boolean isOriginal;

    /**
     * 非原创时作者
     */
    private String author;

    /**
     * 非原创时来源
     */
    private String source;

    /**
     * 私密文章密码
     */
    private String password;

    /**
     * 封面图
     */
    private String thumbnail;

    /**
     * SEO 关键字
     */
    private String seoKeywords;

    /**
     * SEO 描述
     */
    private String seoDesc;

    /**
     * 原文件格式
     */
    @NotBlank(message = "文章内容不能为空")
    private String original;

    /**
     * 内容
     */
    @NotBlank(message = "文章内容不能为空")
    private String content;

    /**
     * 标签
     */
    private List<Object> tags;
}
