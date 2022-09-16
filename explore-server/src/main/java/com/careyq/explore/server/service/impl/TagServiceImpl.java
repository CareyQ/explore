package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.ArticleTag;
import com.careyq.explore.server.entity.Tag;
import com.careyq.explore.server.mapper.TagMapper;
import com.careyq.explore.server.service.ArticleTagService;
import com.careyq.explore.server.service.TagService;
import com.careyq.explore.server.vo.TagVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Service
@AllArgsConstructor
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    private final ArticleTagService articleTagService;

    @Override
    public List<TagVO> getTags() {
        return this.lambdaQuery().list()
                .stream()
                .map(t -> new TagVO(t.getId(), t.getName(), t.getAlias()))
                .toList();
    }

    @Override
    public Result<Boolean> delTag(Long id) {
        this.removeById(id);
        articleTagService.remove(articleTagService.lambdaQuery().eq(ArticleTag::getTagId, id));
        return Result.success("删除成功");
    }
}
