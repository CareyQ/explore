package com.careyq.explore.server.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.service.ConfigService;
import com.careyq.explore.server.vo.ConfigVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

