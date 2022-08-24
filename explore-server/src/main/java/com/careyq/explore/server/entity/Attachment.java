package com.careyq.explore.server.entity;

import com.careyq.explore.common.entity.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Attachment extends BaseModel<Attachment> {

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件路径
     */
    private Integer path;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 图片高度
     */
    private Integer height;

    /**
     * 图片宽度
     */
    private Integer weight;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
