package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.common.exception.UserException;
import com.careyq.explore.common.util.CollUtil;
import com.careyq.explore.common.util.StrUtil;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.dto.ArticleDTO;
import com.careyq.explore.server.dto.ArticlePageDTO;
import com.careyq.explore.server.enmus.ArticleStatusEnum;
import com.careyq.explore.server.entity.ArticleContent;
import com.careyq.explore.server.entity.ArticleTag;
import com.careyq.explore.server.entity.Blog;
import com.careyq.explore.server.entity.Tag;
import com.careyq.explore.server.mapper.BlogMapper;
import com.careyq.explore.server.service.ArticleContentService;
import com.careyq.explore.server.service.ArticleTagService;
import com.careyq.explore.server.service.BlogService;
import com.careyq.explore.server.service.TagService;
import com.careyq.explore.server.vo.ArticlePageVO;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 博客表 服务实现类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Service
@AllArgsConstructor
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    private final ArticleContentService contentService;
    private final TagService tagService;
    private final ArticleTagService articleTagService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveArticle(ArticleDTO dto) {
        if (StrUtil.isNotBlank(dto.getAlias())) {
            Integer exists = baseMapper.selectIsExists(dto.getTitle(), dto.getAlias(), dto.getId());
            if (exists != null) {
                throw new UserException("文章标题或别名已存在");
            }
        }
        Blog article = new Blog();
        BeanUtils.copyProperties(dto, article);
        this.saveOrUpdate(article);

        if (dto.getId() == null) {
            new ArticleContent().setArticleId(article.getId())
                    .setOriginal(dto.getOriginal())
                    .setContent(dto.getContent())
                    .insert();
        } else {
            contentService.lambdaUpdate()
                    .set(ArticleContent::getOriginal, dto.getOriginal())
                    .set(ArticleContent::getContent, dto.getContent())
                    .eq(ArticleContent::getArticleId, article.getId())
                    .update();
        }

        List<Object> tags = dto.getTags();
        List<Long> tagIds = new ArrayList<>();
        List<String> tagNames = new ArrayList<>();
        for (Object tag : tags) {
            if (tag instanceof Number tagId) {
                tagIds.add(Long.valueOf(String.valueOf(tagId)));
            }
            if (tag instanceof String tagName) {
                tagNames.add(tagName);
            }
        }

        List<Tag> newTags = tagNames.stream().map(tagName -> new Tag(tagName, tagName)).toList();
        if (CollUtil.isNotEmpty(newTags)) {
            tagService.saveBatch(newTags);
        }
        tagIds.addAll(newTags.stream().map(Tag::getId).toList());
        List<ArticleTag> articleTags = tagIds.stream().distinct().map(tagId -> new ArticleTag(article.getId(), tagId)).toList();
        if (CollUtil.isNotEmpty(tagIds)) {
            articleTagService.remove(new LambdaQueryWrapper<ArticleTag>()
                    .eq(ArticleTag::getArticleId, article.getId())
                    .notIn(ArticleTag::getTagId, tagIds));
        }
        if (CollUtil.isNotEmpty(articleTags)) {
            articleTagService.saveBatch(articleTags);
        }
        return article.getId();
    }

    @Override
    public Long saveMoment() {
        return null;
    }

    @Override
    public IPage<ArticlePageVO> getArticlePage(ArticlePageDTO dto) {
        IPage<ArticlePageVO> page = baseMapper.selectArticlePage(new Page<>(dto.getCurrent(), dto.getSize()), dto);
        page.getRecords().forEach(e -> e.setTagsName(StrUtil.split(e.getTags(), StrUtil.COMMA)));
        return page;
    }

    @Override
    public Result<ArticleDTO> getArticle(Long id) {
        return Result.success(baseMapper.selectArticle(id));
    }

    @Override
    public Result<Boolean> delArticle(Long id) {
        boolean result = this.removeById(id);
        if (result) {
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Boolean> publishArticle(ArticleDTO dto) {
        Long articleId = this.saveArticle(dto);
        Blog blog = new Blog();
        blog.setStatus(ArticleStatusEnum.PUBLISHED.getCode()).setId(articleId);
        blog.updateById();
        return Result.success("发布成功");
    }
}
