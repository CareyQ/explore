package com.careyq.explore.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.Category;
import com.careyq.explore.server.vo.CategoryVO;

import java.util.List;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
public interface CategoryService extends IService<Category> {

    /**
     * 获取分类列表
     *
     * @return 分类列表
     */
    List<CategoryVO> getCategories();

    /**
     * 更换两个分类位置
     *
     * @param ids 分类 ID
     * @return 结果
     */
    Result<Boolean> exchange(List<Long> ids);
}
