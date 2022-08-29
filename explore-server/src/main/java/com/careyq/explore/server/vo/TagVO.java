package com.careyq.explore.server.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 标签 VO
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-29
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class TagVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 标签别名
     */
    private String alias;

}
