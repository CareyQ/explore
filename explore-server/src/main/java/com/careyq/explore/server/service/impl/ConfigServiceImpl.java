package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.server.entity.Config;
import com.careyq.explore.server.mapper.ConfigMapper;
import com.careyq.explore.server.service.ConfigService;
import com.careyq.explore.server.vo.ConfigVO;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 配置表 服务实现类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {

    @Override
    public String getConfigValue(Integer code) {
        Config config = Optional.ofNullable(this.lambdaQuery()
                .select(Config::getValue)
                .eq(Config::getCode, code)
                .one()).orElseGet(Config::new);
        return config.getValue();
    }

    @Override
    public IPage<ConfigVO> getPage(Integer current, Integer size) {
        return this.lambdaQuery()
                .page(new Page<>(current, size))
                .convert(e -> new ConfigVO(e.getId(), e.getCode(), e.getValue(), e.getConfigDesc()));
    }
}
