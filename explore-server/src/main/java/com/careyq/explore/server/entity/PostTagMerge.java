package com.careyq.explore.server.entity;

import com.careyq.explore.common.entity.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 文章标签关联表
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class PostTagMerge extends BaseModel<PostTagMerge> {

    /**
     * 博客 ID
     */
    private Long postId;

    /**
     * 标签 ID
     */
    private Long tagId;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
