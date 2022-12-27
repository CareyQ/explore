package com.careyq.explore.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.ResourceCategory;
import com.careyq.explore.server.vo.ResourceCategoryPageVO;
import com.careyq.explore.server.vo.ResourceCategoryVO;

import java.util.List;

/**
 * <p>
 * 文件分类表 服务类
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-13
 */
public interface ResourceCategoryService extends IService<ResourceCategory> {

    /**
     * 删除文件分类
     *
     * @param id 文件分类 ID
     * @return 结果
     */
    Result<Boolean> delResourceCategory(Long id);

    /**
     * 获取文件分类列表
     *
     * @return 分类列表
     */
    List<ResourceCategoryPageVO> getList();

    /**
     * 获取文件分类详情
     *
     * @param id 分类 ID
     * @return 详情
     */
    ResourceCategoryVO getDetail(Long id);
}
