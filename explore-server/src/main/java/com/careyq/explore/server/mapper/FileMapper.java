package com.careyq.explore.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.explore.server.entity.File;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文件表 Mapper
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {

}
