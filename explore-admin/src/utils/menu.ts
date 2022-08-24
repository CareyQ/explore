import type { Menu } from '@/interface/Menu'
import type { RouteRecordRaw } from 'vue-router'
import Layout from '@/layout/index.vue'

const modules = import.meta.glob('../views/**/**.vue')

export const generateRouter = (menus: Menu[]) => {
  const routers: RouteRecordRaw[] = menus.map((menu: Menu) => {
    const router: RouteRecordRaw = {
      path: menu.route,
      name: menu.name,
      meta: {
        icon: menu.icon
      },
      component: modules[`../views${menu.route}/${menu.name}.vue`],
      redirect: '',
      children: []
    }
    console.log(`@/views${menu.route}/${menu.name}.vue`)

    if (menu.parentId === 0) {
      router.component = Layout
    }

    if (menu.children && menu.children.length > 0) {
      router.redirect = menu.children[0].route
    }
    if (router && menu.children) {
      router.children = generateRouter(menu.children)
    }
    return router
  })
  return routers
}
