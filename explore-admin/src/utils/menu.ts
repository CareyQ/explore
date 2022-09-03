import type { Menu } from '@/model/menu'
import { NIcon } from 'naive-ui'
import { Icon } from '@iconify/vue'

/**
 * 将后端返回的路由转换为菜单
 * @param menus 路由
 * @returns 菜单
 */
export function convertToMenu(menus: Menu[]) {
  const globalMenu: GlobalMenuOption[] = []
  menus.forEach((menu) => {
    let menuChildren: GlobalMenuOption[] | undefined
    if (menu.children && menu.children.length > 0) {
      menuChildren = convertToMenu(menu.children)
    }
    const menuItem: GlobalMenuOption = addPartialProps({
      menu: {
        key: menu.router,
        label: menu.title,
        routeName: menu.name,
        routePath: menu.router
      },
      icon: menu.icon,
      children: menuChildren
    })

    globalMenu.push(menuItem)
  })

  return globalMenu
}

/** 给菜单添加可选属性 */
function addPartialProps(config: { menu: GlobalMenuOption; icon?: string; children?: GlobalMenuOption[] }) {
  const item = { ...config.menu }
  if (config.icon) {
    Object.assign(item, { icon: iconRender(config.icon) })
  }
  if (config.children) {
    Object.assign(item, { children: config.children })
  }
  return item
}

export function iconRender(icon: string) {
  return () => h(NIcon, null, { default: () => h(Icon, { icon }) })
}
