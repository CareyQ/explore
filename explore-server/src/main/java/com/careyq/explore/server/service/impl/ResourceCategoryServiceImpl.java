package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.ResourceCategory;
import com.careyq.explore.server.mapper.ResourceCategoryMapper;
import com.careyq.explore.server.service.ResourceCategoryService;
import com.careyq.explore.server.service.ResourceService;
import com.careyq.explore.server.vo.ResourceCategoryPageVO;
import com.careyq.explore.server.vo.ResourceCategoryVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文件分类表 服务实现类
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-13
 */
@Service
@AllArgsConstructor
public class ResourceCategoryServiceImpl extends ServiceImpl<ResourceCategoryMapper, ResourceCategory> implements ResourceCategoryService {

    private final ResourceService resourceService;

    @Override
    public Result<Boolean> delResourceCategory(Long id) {
        if (resourceService.isExistByCategory(id)) {
            return Result.fail("该分类下还存在文件");
        }
        this.removeById(id);
        return Result.success("删除成功");
    }

    @Override
    public List<ResourceCategoryPageVO> getList() {
        return baseMapper.selectCategoryList();
    }

    @Override
    public ResourceCategoryVO getDetail(Long id) {
        ResourceCategory category = this.getById(id);
        ResourceCategoryVO vo = new ResourceCategoryVO();
        vo.setId(category.getId())
                .setName(category.getName())
                .setCategoryDesc(category.getCategoryDesc())
                .setPassword(category.getPassword())
                .setIsShow(category.getIsShow());
        return vo;
    }
}
