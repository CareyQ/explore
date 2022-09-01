package com.careyq.explore.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.explore.server.entity.Article;
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
}
