package com.careyq.explore.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.explore.server.entity.Attachment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 文件表 Mapper
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Mapper
public interface AttachmentMapper extends BaseMapper<Attachment> {

    /**
     * 查询指定分类下的文件是否存在
     *
     * @param categoryId 分类 ID
     * @return 结果
     */
    Integer selectExistByCategory(@Param("categoryId") Long categoryId);
}
