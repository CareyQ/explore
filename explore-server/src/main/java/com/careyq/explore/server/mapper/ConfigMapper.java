package com.careyq.explore.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.explore.server.entity.Config;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 配置表 Mapper
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Mapper
public interface ConfigMapper extends BaseMapper<Config> {

}
