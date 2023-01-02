package com.careyq.explore.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.ResourceCategory;
import com.careyq.explore.server.vo.ResourceCategoryPageVO;
import com.careyq.explore.server.vo.ResourceCategoryVO;

import java.util.List;

/**
 * <p>
 * 资源分类表 服务类
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-13
 */
public interface ResourceCategoryService extends IService<ResourceCategory> {

    /**
     * 保存资源分类
     *
     * @param category 资源分类
     * @return 结果
     */
    Result<Boolean> saveResourceCategory(ResourceCategory category);

    /**
     * 删除资源分类
     *
     * @param id 资源分类 ID
     * @return 结果
     */
    Result<Boolean> delResourceCategory(Long id);

    /**
     * 获取资源分类列表
     *
     * @return 分类列表
     */
    List<ResourceCategoryPageVO> getList();

    /**
     * 获取资源分类详情
     *
     * @param id 分类 ID
     * @return 详情
     */
    ResourceCategoryVO getDetail(Long id);
}
