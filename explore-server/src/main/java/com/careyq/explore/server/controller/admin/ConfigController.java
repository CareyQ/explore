package com.careyq.explore.server.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.Config;
import com.careyq.explore.server.service.ConfigService;
import com.careyq.explore.server.vo.ConfigVO;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
     * 删除配置
     *
     * @param id 配置 ID
     * @return 结果
     */
    @DeleteMapping("/del")
    public Result<Boolean> saveConfig(@RequestParam Integer id) {
        return Result.success(configService.removeById(id), "删除成功");
    }
}

