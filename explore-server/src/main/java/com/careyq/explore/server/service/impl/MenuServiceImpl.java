package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.server.entity.Menu;
import com.careyq.explore.server.mapper.MenuMapper;
import com.careyq.explore.server.service.MenuService;
import com.careyq.explore.server.vo.MenuVO;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<MenuVO> getAdminMenus(Integer type) {
        List<Menu> menus = this.lambdaQuery().eq(Menu::getType, type).list();

        Map<Long, MenuVO> result = new LinkedHashMap<>();
        for (Menu menu : menus) {
            MenuVO vo = new MenuVO();
            vo.setId(menu.getId())
                    .setName(menu.getName())
                    .setRoute(menu.getRoute())
                    .setIcon(menu.getIcon())
                    .setIsBlank(menu.getIsBlank())
                    .setTitle(menu.getTitle())
                    .setParentId(menu.getParentId());
            if (Objects.isNull(result.get(menu.getParentId()))) {
                result.put(vo.getId(), vo);
            } else {
                MenuVO parent = result.get(menu.getParentId());
                parent.addChildren(vo);
            }
        }
        return new ArrayList<>(result.values());
    }
}
