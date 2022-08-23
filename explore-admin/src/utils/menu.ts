import type { Menu } from '@/interface/Menu'
import type { RouteRecordRaw } from 'vue-router'
import Layout from '@/layout/index.vue'

const template = import.meta.glob('@/view**.vue')

export const generateRouter = (menus: Menu[]) => {
  const routers: RouteRecordRaw[] = menus.map((menu: Menu) => {
    const router: RouteRecordRaw = {
      path: menu.route,
      name: menu.name,
      meta: {
        icon: menu.icon
      },
      component: template[/* @vite-ignore */ `../view${menu.route}.vue`]
    }

    if (menu.children && menu.children.length > 0) {
      router.redirect = menu.children[0].route
      router.component = Layout
    }
    if (router && menu.children) {
      router.children = generateRouter(menu.children)
    }
    return router
  })
  return routers
}
