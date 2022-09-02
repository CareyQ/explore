import { defineStore } from 'pinia'
import { getMenus } from '@/apis/menu'
import { ref, shallowRef } from 'vue'
import Layout from '@/views/layout/index.vue'

import type { RouteRecordRaw } from 'vue-router'
import type { Menu } from '@/model/menu'

export const useRouterStore = defineStore('router', () => {
  // 存放动态路由
  const routes = ref(<RouteRecordRaw[]>[])

  const asyncRouter = async () => {
    const baseRouter: RouteRecordRaw[] = [
      {
        path: '/',
        name: 'Layout',
        component: shallowRef(Layout),
        meta: {
          title: 'Layout'
        },
        children: [] as RouteRecordRaw[]
      }
    ]

    try {
      const { data } = await getMenus()
      if (data) {
        const asyncRoutes = generateRouter(data)
        baseRouter[0].children = asyncRoutes
        routes.value = baseRouter
      }
    } catch (err) {
      console.log(err)
    }

    return true
  }

  return {
    routes,
    asyncRouter
  }
})

const modules = import.meta.glob('../**/**.vue')
const generateRouter = (menus: Menu[]) => {
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
