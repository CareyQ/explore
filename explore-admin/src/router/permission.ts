import { useRouterStore } from '@/stores/modules/menu'
import router from '@/router'

import type { RouteRecordRaw } from 'vue-router'

const getRouter = async () => {
  const routerStore = useRouterStore()
  await routerStore.asyncRouter()
  console.log('加载路由')

  const asyncRouters = routerStore.routes
  asyncRouters.forEach((asyncRouter: RouteRecordRaw) => {
    router.addRoute(asyncRouter)
  })
}

router.beforeEach(async (to, from, next) => {
  await getRouter()
  next()
})
