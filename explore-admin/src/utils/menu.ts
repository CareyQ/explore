import type { Menu } from '@/interface/Menu'
import type { RouteRecordRaw } from 'vue-router'

export const generateRouter = (menus: Menu[]) => {
  const routers: RouteRecordRaw[] = menus.map((menu: Menu) => {
    const router: RouteRecordRaw = {
      path: menu.router,
      name: menu.name,
      meta: {
        icon: menu.icon,
        title: menu.title,
        requireAuth: true
      },
      redirect: '',
      component: () => import(/* @vite-ignore */ `../views/${menu.component}`),
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
