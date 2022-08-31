package com.careyq.explore.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.dto.ArticleDTO;
import com.careyq.explore.server.entity.Article;

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
}
