package com.careyq.explore.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.PostCategoryTag;
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
public interface PostCategoryTagService extends IService<PostCategoryTag> {

    /**
     * 获取分类列表
     *
     * @return 分类列表
     */
    List<CategoryVO> getCategories();

    /**
     * 根据 id 删除分类
     *
     * @param id 分类 ID
     * @return 结果
     */
    Result<Boolean> delCategory(Long id);
}
