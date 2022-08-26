import { defineStore } from 'pinia'
import { getMenu } from '@/api/menu'
import Layout from '@/layout/index.vue'
import { generateRouter } from '@/utils/menu'
import type { RouteRecordRaw } from 'vue-router'
import { ref, shallowRef } from 'vue'
import { useUserStore } from '@/stores/modules/user'
import cookie from 'js-cookie'

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
      const { data } = await getMenu()
      if (data) {
        const asyncRoutes = generateRouter(data)
        baseRouter[0].children = asyncRoutes
        routes.value = baseRouter
      }
    } catch (err) {
      const userStore = useUserStore()
      cookie.remove('token')
      userStore.token = ''
      userStore.userInfo = ''
    }

    return true
  }

  return {
    routes,
    asyncRouter
  }
})
