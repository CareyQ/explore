package com.careyq.explore.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.explore.server.entity.Menu;
import com.careyq.explore.server.vo.MenuVO;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
public interface MenuService extends IService<Menu> {

    /**
     * 获取 admin 菜单
     *
     * @param type 菜单类型
     * @return 菜单列表
     */
    List<MenuVO> getAdminMenus(Integer type);
}
