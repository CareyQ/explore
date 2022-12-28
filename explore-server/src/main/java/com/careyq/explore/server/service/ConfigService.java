package com.careyq.explore.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.explore.server.entity.Config;
import com.careyq.explore.server.vo.ConfigVO;

/**
 * <p>
 * 配置表 服务类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
public interface ConfigService extends IService<Config> {

    /**
     * 根据 Code 获取值
     *
     * @param code code
     * @return 值
     */
    String getConfigValue(Integer code);

    IPage<ConfigVO> getPage(Integer current, Integer size);
}
