package com.careyq.explore.server.entity;

import com.careyq.explore.common.entity.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 评论留言表
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseModel<Comment> {

    /**
     * 评论来源 0：文章 1：独立页面 2：自定义页面
     */
    private Integer source;

    /**
     * 来源 ID
     */
    private Long sourceId;

    /**
     * 评论人名称
     */
    private String author;

    /**
     * 评论人邮箱
     */
    private String email;

    /**
     * 评论人 url
     */
    private String url;

    /**
     * 评论状态 0：待审核 1：已审核 2：回收
     */
    private Integer status;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论人设备
     */
    private String userAgent;

    /**
     * 是否是管理员（作者）
     */
    private Boolean isAdmin;

    /**
     * 父级 ID
     */
    private Long parentId;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
