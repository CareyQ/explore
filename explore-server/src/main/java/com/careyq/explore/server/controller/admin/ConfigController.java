package com.careyq.explore.server.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.explore.common.util.CollUtil;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.Config;
import com.careyq.explore.server.service.ConfigService;
import com.careyq.explore.server.vo.ConfigVO;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 配置表 控制层
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/config")
public class ConfigController {

    private final ConfigService configService;

    /**
     * 获取配置分页
     *
     * @return 分页
     */
    @GetMapping("/page")
    public Result<IPage<ConfigVO>> getPage(@RequestParam Integer current, @RequestParam Integer size) {
        return Result.success(configService.getPage(current, size));
    }

    /**
     * 保存配置
     *
     * @param config 配置
     * @return 结果
     */
    @PostMapping("/save")
    public Result<Boolean> saveConfig(@Validated @RequestBody Config config) {
        return Result.success(configService.saveConfig(config), "保存成功");
    }

    /**
     * 保存配置
     *
     * @param configs 配置
     * @return 结果
     */
    @PostMapping("/batch/save")
    public Result<Boolean> batchSaveConfig(@RequestBody List<Config> configs) {
        return Result.success(configService.batchSaveConfig(configs), "保存成功");
    }

    /**
     * 获取配置
     *
     * @param code 配置 code
     * @return 结果
     */
    @PostMapping("/list")
    public Result<List<ConfigVO>> getConfigs(@RequestBody List<Integer> code) {
        if (CollUtil.isEmpty(code)) {
            return Result.fail("配置 Code 不能为空");
        }
        List<ConfigVO> configs = configService.lambdaQuery()
                .in(Config::getCode, code).list()
                .stream().map(e -> new ConfigVO(e.getId(), e.getCode(), e.getValue(), null))
                .toList();
        return Result.success(configs);
    }

    /**
     * 删除配置
     *
     * @param ids 配置 ID
     * @return 结果
     */
    @DeleteMapping("/del")
    public Result<Boolean> batchDel(@RequestBody List<Integer> ids) {
        return Result.success(configService.removeByIds(ids), "删除成功");
    }
}

