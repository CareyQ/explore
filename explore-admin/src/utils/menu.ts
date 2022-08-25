import type { Menu } from '@/interface/Menu'
import type { RouteRecordRaw } from 'vue-router'

const modules = import.meta.glob('../views/**/**.vue')
const childModules = import.meta.glob('../views/**/**/**.vue')

export const generateRouter = (menus: Menu[], isChlid = false) => {
  const routers: RouteRecordRaw[] = menus.map((menu: Menu) => {
    const router: RouteRecordRaw = {
      path: menu.router,
      name: menu.name,
      meta: {
        icon: menu.icon,
        title: menu.title
      },
      redirect: '',
      component: () => import(/* @vite-ignore */ `../views/${menu.component}`),
      children: []
    }
    console.log(router.component)

    if (menu.children && menu.children.length > 0) {
      router.redirect = menu.children[0].router
    }
    if (router && menu.children) {
      router.children = generateRouter(menu.children, true)
    }
    return router
  })
  return routers
}
