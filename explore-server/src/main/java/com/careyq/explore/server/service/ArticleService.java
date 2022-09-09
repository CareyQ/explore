package com.careyq.explore.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.dto.ArticleDTO;
import com.careyq.explore.server.dto.ArticlePageDTO;
import com.careyq.explore.server.entity.Article;
import com.careyq.explore.server.vo.ArticlePageVO;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
public interface ArticleService extends IService<Article> {

    /**
     * 保存文章
     *
     * @param dto 文章
     * @return 结果
     */
    Result<Boolean> saveArticle(ArticleDTO dto);

    /**
     * 获取文章分页
     *
     * @param dto ArticlePageDTO
     * @return 分页
     */
    IPage<ArticlePageVO> getArticlePage(ArticlePageDTO dto);
}
