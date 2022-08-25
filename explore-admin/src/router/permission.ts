import { useUserStore } from '@/stores/modules/user'
import { useRouterStore } from '@/stores/modules/router'
import router from '@/router'
import type { RouteRecordName } from 'vue-router'

const whiteList: RouteRecordName[] = ['Login']

const getRouter = async () => {
  const routerStore = useRouterStore()
  await routerStore.asyncRouter()
  const asyncRouters = routerStore.routes
  asyncRouters.forEach((asyncRouter) => {
    router.addRoute(asyncRouter)
  })
}

router.beforeEach(async (to, from, next) => {
  console.log('从' + from.path + '到' + to.path)

  const userStore = useUserStore()
  const token = userStore.token
  const isToLogin = to.name === 'Login'

  if (token) {
    if (isToLogin) {
      next({ name: 'Dashboard' })
    } else {
    }
  }

  // 前往登录界面的
  if (isToLogin) {
    // 已登录
    if (token) {
      await getRouter()
      if (userStore.userInfo) {
        return { name: 'Dashboard' }
      } else {
        userStore.ClearStorage()
        return { name: 'Login' }
      }
    } else {
      return true
    }
  } else {
    // 前往其他界面，已登录
    if (token) {
      if (from.name && !isToLogin) {
        await getRouter()
        if (userStore.token) {
          console.log('replace')

          return { ...to, replace: true }
        } else {
          return {
            name: 'Login',
            query: { redirect: to.path }
          }
        }
      } else {
        if (to.matched.length) {
          return true
        } else {
          return { path: '/login' }
        }
      }
    }

    if (!token) {
      return {
        name: 'Login',
        query: {
          redirect: document.location.hash
        }
      }
    }
  }
})
