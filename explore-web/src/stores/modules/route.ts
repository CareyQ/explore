import router from '@/router'
import Layout from '@/views/layout/index.vue'
import { menusRes } from '@/mock/system'
import type { RouteRecordRaw } from 'vue-router'
import { Icon } from '@iconify/vue'

interface RouteState {
  routes: RouteRecordRaw[]
  menus: RouteRecordRaw[][]
}

export const useRouteStore = defineStore('route-store', {
  state: (): RouteState => ({
    routes: [],
    menus: []
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
      this.menus = convertToMenu(this.routes)
      root.children = this.routes
      router.addRoute(root)
    },

    async initRoute() {
      this.handleRoutes(menusRes)
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
        title: menu.title,
        group: menu.group
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

function convertToMenu(routers: RouteRecordRaw[]) {
  const groupBy = (array: RouteRecordRaw[], f: Function) => {
    const groups: {
      [key: string]: RouteRecordRaw[]
    } = {}
    array.forEach((item) => {
      const group = JSON.stringify(f(item))
      groups[group] = groups[group] || []
      groups[group].push(item)
    })
    return Object.keys(groups).map((group) => {
      return groups[group]
    })
  }
  const menu = groupBy(routers, (item: RouteRecordRaw) => {
    if (item.meta) return item?.meta.group
  })
  return menu
}

export function iconRender(icon: string) {
  return () => h(Icon, { icon })
}
