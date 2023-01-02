package com.careyq.explore.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.explore.server.entity.Config;
import com.careyq.explore.server.vo.ConfigVO;

import java.util.List;

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

    /**
     * 查询配置分页
     *
     * @param current 当前页
     * @param size    页面大小
     * @return 分页
     */
    IPage<ConfigVO> getPage(Integer current, Integer size);

    /**
     * 保存配置
     *
     * @param config 配置
     * @return 结果
     */
    boolean saveConfig(Config config);

    /**
     * 批量保存配置
     *
     * @param configs 配置
     * @return 结果
     */
    boolean batchSaveConfig(List<Config> configs);
}
