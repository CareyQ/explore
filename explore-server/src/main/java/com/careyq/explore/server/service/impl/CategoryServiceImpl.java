package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.common.util.CollUtil;
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

    /**
     * 分类顺序交换 id 容量
     */
    private static final int EXCHANGE_ID_SIZE = 2;

    @Override
    public List<CategoryVO> getCategories() {
        return baseMapper.selectCategories();
    }

    @Override
    public Result<Boolean> exchange(List<Long> ids) {
        if (CollUtil.isEmpty(ids) || ids.size() != EXCHANGE_ID_SIZE) {
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
