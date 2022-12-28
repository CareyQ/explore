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
public class Post extends BaseModel<Post> {

    /**
     * 类型：0：文章 1：片刻
     */
    private Integer type;

    /**
     * 文章别名，路径标识
     */
    private String slug;

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
     * 文章摘要/片刻内容
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String desc;

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
     * 归属地
     */
    private String location;

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
     * 关联附件 文章：封面图 片刻：附件
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String file_id;

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
    private Long word_count;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
