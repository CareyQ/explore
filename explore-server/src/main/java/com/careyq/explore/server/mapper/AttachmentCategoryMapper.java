package com.careyq.explore.server.mapper;

import com.careyq.explore.server.entity.AttachmentCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.explore.server.vo.AttachmentCategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 文件分类表 Mapper
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-13
 */
@Mapper
public interface AttachmentCategoryMapper extends BaseMapper<AttachmentCategory> {

    /**
     * 查询文件分类列表
     *
     * @return 结果
     */
    List<AttachmentCategoryVO> selectCategoryList();
}
