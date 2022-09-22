package com.careyq.explore.server.vo;

import com.careyq.explore.common.annotation.FileUrl;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 附件 VO
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Data
@Accessors(chain = true)
public class AttachmentVO {

    /**
     * 主键
     */
    private Long id;

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
    @FileUrl
    private String path;

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

    /**
     * 上传时间
     */
    private LocalDateTime createTime;

}
