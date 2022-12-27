package com.careyq.explore.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.explore.server.entity.PostMeta;
import com.careyq.explore.server.vo.CategoryVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 分类表 Mapper
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
public interface PostMetaMapper extends BaseMapper<PostMeta> {

    /**
     * 查询名称或别名是否已存在
     *
     * @param name      名称
     * @param slug     别名
     * @param id        主键
     * @return 结果
     */
    Integer selectIsExists(@Param("name") String name,
                           @Param("slug") String slug,
                           @Param("id") Long id);

    /**
     * 查询分类
     *
     * @return 分类列表
     */
    List<CategoryVO> selectCategories();

    /**
     * 根据分类查询文章是否存在
     *
     * @param categoryId 分类 ID
     * @return 是否存在
     */
    @Select("select 1 from article where is_del = 0 and category_id = #{categoryId} limit 1")
    Integer selectArticleIsExistByCategoryId(@Param("categoryId") Long categoryId);
}
