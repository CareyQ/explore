package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.common.util.CollUtil;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.Category;
import com.careyq.explore.server.mapper.CategoryMapper;
import com.careyq.explore.server.service.CategoryService;
import com.careyq.explore.server.vo.CategoryVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public Result<Boolean> saveCategory(Category category) {
        Integer exists = baseMapper.selectIsExists(category.getName(), category.getAlias(), category.getId());
        if (exists != null) {
            return Result.fail("名称或别名已存在");
        }
        boolean result = category.getId() == null ? category.insert() : category.updateById();
        return Result.success(result, "保存成功");
    }

    @Override
    public List<CategoryVO> getCategories() {
        List<Category> categories = this.lambdaQuery().orderByAsc(Category::getSort).list();

        return categories.stream().map(c -> {
            CategoryVO vo = new CategoryVO();
            vo.setId(c.getId())
                    .setName(c.getName())
                    .setAlias(c.getAlias())
                    .setHits(c.getHits())
                    .setSort(c.getSort());
            return vo;
        }).toList();
    }

    @Override
    public Result<Boolean> exchange(List<Long> ids) {
        if (CollUtil.isEmpty(ids) || ids.size() != 2) {
            return Result.fail("操作的数据有误");
        }
        List<Category> list = this.lambdaQuery().select(Category::getId, Category::getSort).in(Category::getId, ids).list();
        Category first = list.get(0);
        Category second = list.get(1);
        Integer tempSort = first.getSort();
        first.setSort(second.getSort());
        second.setSort(tempSort);
        this.updateBatchById(list, list.size());
        return Result.success("操作成功");
    }
}
