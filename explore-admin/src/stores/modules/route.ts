import router from '@/router'
import Layout from '@/views/layout/index.vue'
import { getMenus } from '@/apis/menu'
import { convertToMenu } from '@/utils/menu'
import type { Menu } from '@/model/menu'
import type { RouteRecordRaw } from 'vue-router'

interface RouteState {
  menus: GlobalMenuOption[]
}

export const useRouteStore = defineStore('route-store', {
  state: (): RouteState => ({
    menus: []
  }),
  actions: {
    handleRoutes(routes: Menu[]) {
      ;(this.menus as GlobalMenuOption[]) = convertToMenu(routes)

      const root: RouteRecordRaw = {
        path: '/',
        name: 'Layout',
        component: Layout,
        redirect: '/dashboard',
        meta: {
          title: 'Layout'
        },
        children: [] as RouteRecordRaw[]
      }

      const vueRoutes = convertToRouter(routes)
      root.children = vueRoutes
      router.addRoute(root)
    },
    async initRoute() {
      const { data } = await getMenus()
      if (data) {
        this.handleRoutes(data)
      }
    }
  }
})

/** 后端路由转换为 vue 路由 */
function convertToRouter(routes: Menu[]) {
  const modules = import.meta.glob('../../views/**/*.vue')
  return routes.map((menu) => {
    const router: RouteRecordRaw = {
      path: menu.router,
      name: menu.name,
      meta: {
        icon: menu.icon,
        title: menu.title
      },
      redirect: '',
      component: modules[`../../views/${menu.component}.vue`],
      children: []
    }

    if (menu.children && menu.children.length > 0) {
      router.redirect = menu.children[0].router
    }
    if (router && menu.children) {
      router.children = convertToRouter(menu.children)
    }

    return router
  })
}
