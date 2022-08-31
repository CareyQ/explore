package com.careyq.explore.server.entity;

import com.careyq.explore.common.entity.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 文章内容表
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ArticleContent extends BaseModel<ArticleContent> {

    /**
     * 文章 ID
     */
    private Long articleId;

    /**
     * 原文件格式
     */
    private String original;

    /**
     * 文章内容
     */
    private String content;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
