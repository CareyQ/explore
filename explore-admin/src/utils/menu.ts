import type { Menu } from '@/interface/Menu'
import type { RouteRecordRaw } from 'vue-router'

const modules = import.meta.glob('../**/**.vue')

export const generateRouter = (menus: Menu[]) => {
  const routers: RouteRecordRaw[] = menus.map((menu: Menu) => {
    const router: RouteRecordRaw = {
      path: menu.router,
      name: menu.name,
      meta: {
        icon: menu.icon,
        title: menu.title
      },
      redirect: '',
      component: modules['../views/' + menu.component + '.vue'],
      children: []
    }

    if (menu.children && menu.children.length > 0) {
      router.redirect = menu.children[0].router
    }
    if (router && menu.children) {
      router.children = generateRouter(menu.children)
    }
    return router
  })
  return routers
}
