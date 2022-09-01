package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.common.util.CollUtil;
import com.careyq.explore.common.util.StrUtil;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.dto.ArticleDTO;
import com.careyq.explore.server.entity.Article;
import com.careyq.explore.server.entity.ArticleContent;
import com.careyq.explore.server.entity.ArticleTag;
import com.careyq.explore.server.entity.Tag;
import com.careyq.explore.server.mapper.ArticleMapper;
import com.careyq.explore.server.service.ArticleContentService;
import com.careyq.explore.server.service.ArticleService;
import com.careyq.explore.server.service.ArticleTagService;
import com.careyq.explore.server.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Service
@AllArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    private final ArticleContentService contentService;
    private final TagService tagService;
    private final ArticleTagService articleTagService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Boolean> saveArticle(ArticleDTO dto) {
        if (StrUtil.isNotBlank(dto.getAlias())) {
            Integer exists = baseMapper.selectIsExists(dto.getTitle(), dto.getAlias(), dto.getId());
            if (exists != null) {
                return Result.fail("文章标题或别名已存在");
            }
        }
        Article article = new Article();
        BeanUtils.copyProperties(dto, article);
        this.saveOrUpdate(article);

        ArticleContent articleContent = new ArticleContent();
        articleContent.setArticleId(article.getId())
                .setOriginal(dto.getOriginal())
                .setContent(dto.getContent());
        contentService.saveOrUpdate(articleContent);

        List<Object> tags = dto.getTags();
        if (CollUtil.isEmpty(tags)) {
            return Result.success("保存成功");
        }

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
        return Result.success("保存成功");
    }
}
