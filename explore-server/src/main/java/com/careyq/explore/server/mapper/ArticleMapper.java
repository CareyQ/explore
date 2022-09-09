package com.careyq.explore.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.careyq.explore.server.dto.ArticlePageDTO;
import com.careyq.explore.server.entity.Article;
import com.careyq.explore.server.vo.ArticlePageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 文章表 Mapper
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 查询名称或别名是否已存在
     *
     * @param title 标题
     * @param alias 别名
     * @param id    主键
     * @return 结果
     */
    Integer selectIsExists(@Param("title") String title,
                           @Param("alias") String alias,
                           @Param("id") Long id);

    /**
     * 查询文章分页
     *
     * @param page 分页
     * @param dto  筛选条件
     * @return 结果
     */
    IPage<ArticlePageVO> selectArticlePage(Page<ArticlePageVO> page,
                                           @Param("dto") ArticlePageDTO dto);
}
