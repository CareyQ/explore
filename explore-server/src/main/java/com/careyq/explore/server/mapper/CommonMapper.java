package com.careyq.explore.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 可公共使用 Mapper
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-29
 */
@Mapper
public interface CommonMapper<T> extends BaseMapper<T> {

    /**
     * 查询名称或别名是否已存在
     *
     * @param name      名称
     * @param alias     别名
     * @param id        主键
     * @param tableName 表名
     * @return 结果
     */
    Integer selectIsExists(@Param("name") String name,
                           @Param("alias") String alias,
                           @Param("id") Long id,
                           @Param("tableName") String tableName);

}
