package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.PostMeta;
import com.careyq.explore.server.mapper.PostMetaMapper;
import com.careyq.explore.server.service.PostMetaService;
import com.careyq.explore.server.vo.PostMetaVO;
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
public class PostMetaServiceImpl extends ServiceImpl<PostMetaMapper, PostMeta> implements PostMetaService {

    @Override
    public Result<Boolean> saveMeta(PostMeta meta) {
        Integer exists = baseMapper.selectIsExists(meta);
        if (exists != null) {
            return Result.fail("名称或别名已存在");
        }
        boolean result = this.saveOrUpdate(meta);
        return Result.success(result, "保存成功");
    }

    @Override
    public List<PostMetaVO> getCategories() {
        return baseMapper.selectMetaList();
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
