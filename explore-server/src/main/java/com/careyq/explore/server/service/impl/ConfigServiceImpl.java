package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.common.exception.UserException;
import com.careyq.explore.common.util.CollUtil;
import com.careyq.explore.server.entity.Config;
import com.careyq.explore.server.mapper.ConfigMapper;
import com.careyq.explore.server.service.ConfigService;
import com.careyq.explore.server.vo.ConfigVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        return this.page(new Page<>(current, size)).convert(e -> new ConfigVO(e.getId(), e.getCode(), e.getValue(), e.getConfigDesc()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveConfig(Config config) {
        Long count = this.lambdaQuery()
                .eq(Config::getCode, config.getCode())
                .ne(config.getId() != null, Config::getId, config.getId())
                .count();
        if (count != null && count > 0) {
            throw new UserException("配置 code 已存在");
        }
        return this.saveOrUpdate(config);
    }

    @Override
    public boolean batchSaveConfig(List<Config> configs) {
        if (CollUtil.isEmpty(configs)) {
            throw new UserException("配置不能为空");
        }
        Set<Integer> codes = configs.stream().map(Config::getCode).collect(Collectors.toSet());
        Map<Integer, Long> oldConfigMap = this.lambdaQuery()
                .in(Config::getCode, codes).list()
                .stream().collect(Collectors.toMap(Config::getCode, Config::getId));
        for (Config config : configs) {
            if (config.getCode() == null) {
                throw new UserException("配置 Code 不能为空");
            }
            Long id = oldConfigMap.get(config.getCode());
            if (id != null && !id.equals(config.getId())) {
                throw new UserException("配置 Code 不能重复");
            }
        }
        return this.saveOrUpdateBatch(configs);
    }
}
