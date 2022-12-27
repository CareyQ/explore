package com.careyq.explore.server.controller.admin;

import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.enmus.MenuTypeEnum;
import com.careyq.explore.server.service.MenuService;
import com.careyq.explore.server.vo.MenuVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单表 控制层
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    /**
     * 获取后台菜单
     *
     * @return 菜单列表
     */
    @GetMapping("/admin/list")
    public Result<List<MenuVO>> getAdminMenus() {
        return Result.success(menuService.getAdminMenus(MenuTypeEnum.ADMIN.getCode()));
    }
}

