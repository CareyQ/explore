package com.careyq.explore.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.dto.ArticleDTO;
import com.careyq.explore.server.dto.ArticlePageDTO;
import com.careyq.explore.server.entity.BlogPost;
import com.careyq.explore.server.vo.ArticlePageVO;

/**
 * <p>
 * 博客表 服务类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
public interface BlogService extends IService<BlogPost> {

    /**
     * 保存文章
     *
     * @param dto 文章参数
     * @return 结果
     */
    Long saveArticle(ArticleDTO dto);

    Long saveMoment();

    /**
     * 获取文章分页
     *
     * @param dto ArticlePageDTO
     * @return 分页
     */
    IPage<ArticlePageVO> getArticlePage(ArticlePageDTO dto);

    /**
     * 获取文章详情
     *
     * @param id 文章 ID
     * @return 文章详情
     */
    Result<ArticleDTO> getArticle(Long id);

    /**
     * 删除文章
     *
     * @param id 文章 ID
     * @return 操作结果
     */
    Result<Boolean> delArticle(Long id);

    /**
     * 发布文章
     *
     * @param dto 文章参数
     * @return 操作结果
     */
    Result<Boolean> publishArticle(ArticleDTO dto);
}
