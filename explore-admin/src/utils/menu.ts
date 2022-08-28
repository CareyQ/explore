import type { Menu } from '@/interface/Menu'
import type { RouteRecordRaw } from 'vue-router'

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
      component: getViews(menu.component),
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

function getViews(path: string) {
  // 首先把你需要动态路由的组件地址全部获取
  const modules = import.meta.glob('../**/*.vue')
  // 然后动态路由的时候这样来取
  return modules['../views/' + path + '.vue']
}
