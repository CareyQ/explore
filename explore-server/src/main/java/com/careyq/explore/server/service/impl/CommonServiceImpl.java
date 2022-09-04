package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.common.entity.BaseModel;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.Category;
import com.careyq.explore.server.entity.Tag;
import com.careyq.explore.server.mapper.CommonMapper;
import com.careyq.explore.server.service.CommonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * <p>
 * 可公共使用 服务实现类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-29
 */
@Service
@AllArgsConstructor
public class CommonServiceImpl<T extends BaseModel<T>> extends ServiceImpl<CommonMapper<T>, T> implements CommonService<T> {

    private final Map<String, IService<T>> serviceMap;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Boolean> saveEntity(T entity) {
        Long id;
        String name;
        String alias;
        String tableName;
        IService<T> service;
        switch (entity) {
            case Category category -> {
                id = category.getId();
                name = category.getName();
                alias = category.getAlias();
                tableName = "category";
                service = serviceMap.get("categoryServiceImpl");
            }
            case Tag tag -> {
                id = tag.getId();
                name = tag.getName();
                alias = tag.getAlias();
                tableName = "tag";
                service = serviceMap.get("tagServiceImpl");
            }
            default -> throw new IllegalStateException("Unexpected value: " + entity);
        }

        if (service == null) {
            return Result.fail("服务获取失败");
        }

        Integer exists = baseMapper.selectIsExists(name, alias, id, tableName);
        if (exists != null) {
            return Result.fail("名称或别名已存在");
        }
        boolean result = service.saveOrUpdate(entity);
        return Result.success(result, "保存成功");
    }
}
