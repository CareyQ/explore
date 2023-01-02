package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.common.exception.UserException;
import com.careyq.explore.common.util.ConfigUtil;
import com.careyq.explore.common.util.StrUtil;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.enmus.ConfigEnum;
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
    public Result<Boolean> saveResourceCategory(ResourceCategory category) {
        Long count = this.lambdaQuery()
                .eq(ResourceCategory::getName, category.getName())
                .ne(category.getId() != null, ResourceCategory::getId, category.getId())
                .count();
        if (count != null && count > 0) {
            return Result.fail("资源分类名称已存在");
        }
        this.saveOrUpdate(category);
        return Result.success("保存成功");
    }

    @Override
    public Result<Boolean> delResourceCategory(Long id) {
        String config = ConfigUtil.getConfig(ConfigEnum.DEFAULT_RESOURCE_CATEGORY.getCode());
        if (StrUtil.isNotBlank(config) && id.equals(Long.valueOf(config))) {
            return Result.fail("默认资源文件夹不可删除");
        }
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
        if (category == null) {
            throw new UserException("该资源分类不存在");
        }
        ResourceCategoryVO vo = new ResourceCategoryVO();
        vo.setId(category.getId())
                .setName(category.getName())
                .setCategoryDesc(category.getCategoryDesc())
                .setPassword(category.getPassword())
                .setIsShow(category.getIsShow());
        return vo;
    }
}
