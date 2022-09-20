import router from '@/router'
import Layout from '@/views/layout/index.vue'
import { getMenus } from '@/service/api/menu'
import type { RouteRecordRaw } from 'vue-router'
import { Icon } from '@iconify/vue'
import { ElIcon } from 'element-plus'

interface RouteState {
  routes: RouteRecordRaw[]
}

export const useRouteStore = defineStore('route-store', {
  state: (): RouteState => ({
    routes: []
  }),

  actions: {
    handleRoutes(menus: Menu[]) {
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

      this.routes = convertToRouter(menus)
      root.children = this.routes
      router.addRoute(root)
    },

    async initRoute() {
      const { data } = await getMenus()

      if (data) {
        this.handleRoutes(data as Menu[])
      }
    }
  }
})

/** 后端路由转换为 vue 路由 */
const modules = import.meta.glob('../../views/**/**.vue')
function convertToRouter(menus: Menu[]) {
  return menus.map((menu) => {
    const router: RouteRecordRaw = {
      path: menu.router,
      name: menu.name,
      meta: {
        title: menu.title
      },
      redirect: '',
      component: modules[`../../views/${menu.component}.vue`],
      children: []
    }

    if (menu.icon && router.meta) {
      router.meta.icon = iconRender(menu.icon)
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

export function iconRender(icon: string) {
  return () => h(ElIcon, null, { default: () => h(Icon, { icon }) })
}
