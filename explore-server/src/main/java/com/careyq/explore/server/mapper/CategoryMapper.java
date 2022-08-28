package com.careyq.explore.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.explore.server.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 分类表 Mapper
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    @Select("select max(sort) from category where is_del = 0")
    Integer selectMaxSort();

    Integer selectIsExists(@Param("name") String name,
                                      @Param("alias") String alias,
                                      @Param("id") Long id);

}
