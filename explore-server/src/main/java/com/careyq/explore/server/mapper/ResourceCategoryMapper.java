package com.careyq.explore.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.explore.server.entity.ResourceCategory;
import com.careyq.explore.server.vo.ResourceCategoryPageVO;

import java.util.List;

/**
 * <p>
 * 文件分类表 Mapper
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-13
 */
public interface ResourceCategoryMapper extends BaseMapper<ResourceCategory> {

    /**
     * 查询文件分类列表
     *
     * @return 结果
     */
    List<ResourceCategoryPageVO> selectCategoryList();
}
