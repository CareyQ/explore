package com.careyq.explore.server.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.common.util.RedisUtil;
import com.careyq.explore.common.util.StrUtil;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.constant.CacheConst;
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
        String cache = RedisUtil.get(CacheConst.MENU_KEY);
        if (StrUtil.isNotBlank(cache)) {
            return JSON.parseObject(cache, new TypeReference<List<MenuVO>>() {
            });
        }
        List<Menu> menus = this.lambdaQuery().eq(Menu::getType, type).list();

        Map<Long, MenuVO> result = new LinkedHashMap<>();
        for (Menu menu : menus) {
            MenuVO vo = new MenuVO();
            vo.setId(menu.getId())
                    .setName(menu.getName())
                    .setRouter(menu.getRouter())
                    .setIcon(menu.getIcon())
                    .setIsBlank(menu.getIsBlank())
                    .setTitle(menu.getTitle())
                    .setComponent(menu.getComponent());
            if (Objects.isNull(result.get(menu.getParentId()))) {
                result.put(vo.getId(), vo);
            } else {
                MenuVO parent = result.get(menu.getParentId());
                parent.addChildren(vo);
            }
        }
        RedisUtil.set(CacheConst.MENU_KEY, result.values());
        return new ArrayList<>(result.values());
    }

    @Override
    public Result<Boolean> delMenu(Long id) {
        this.removeById(id);
        return Result.success("删除成功");
    }
}
