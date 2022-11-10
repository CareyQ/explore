package com.careyq.explore.server.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.careyq.explore.common.entity.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 博客表
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Blog extends BaseModel<Blog> {

    /**
     * 文章别名，路径标识
     */
    private String alias;

    /**
     * 类型：0：文章 1：片刻
     */
    private Integer type;

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
     * 文章摘要/片刻
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
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
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String author;

    /**
     * 非原创时来源
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String source;

    /**
     * 私密文章密码
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String password;

    /**
     * 附件 ID
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String attachmentIds;

    /**
     * SEO 关键字
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String seoKeywords;

    /**
     * SEO 描述
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String seoDesc;

    /**
     * 发表位置
     */
    private String location;

    /**
     * 文章浏览量
     */
    private Long hits;

    /**
     * 文章点赞数
     */
    private Long likes;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
