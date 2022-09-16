package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.Category;
import com.careyq.explore.server.mapper.CategoryMapper;
import com.careyq.explore.server.service.CategoryService;
import com.careyq.explore.server.vo.CategoryVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<CategoryVO> getCategories() {
        return baseMapper.selectCategories();
    }

    @Override
    public Result<Boolean> delCategory(Long id) {
        Integer exist = baseMapper.selectArticleIsExistByCategoryId(id);
        if (exist != null) {
            return Result.fail("该分类下还存在文章，不可删除");
        }
        this.removeById(id);
        return Result.success("删除成功");
    }
}
