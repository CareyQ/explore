package com.careyq.explore.server.entity;

import com.careyq.explore.common.entity.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Article extends BaseModel<Article> {

    /**
     * 文章别名，路径标识
     */
    private String alias;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章状态 0：草稿 1：发布
     */
    private Integer status;

    /**
     * 分类 ID
     */
    private Long categoryId;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 允许评论 0：禁止 1：允许
     */
    private Boolean allowComment;

    /**
     * 是否置顶 0：否 1：是
     */
    private Boolean isTop;

    /**
     * 是否是原创 0：否 1：是
     */
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
     * 文章浏览量
     */
    private Long hits;

    /**
     * 文章点赞数
     */
    private Long likes;

    /**
     * 文章内容字数统计
     */
    private Long wordCount;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
