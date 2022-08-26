import { useUserStore } from '@/stores/modules/user'
import { useRouterStore } from '@/stores/modules/router'
import router from '@/router'

const getRouter = async () => {
  const routerStore = useRouterStore()
  await routerStore.asyncRouter()
  const asyncRouters = routerStore.routes
  asyncRouters.forEach((asyncRouter) => {
    router.addRoute(asyncRouter)
  })
}

router.beforeEach(async (to, from, next) => {
  const userStore = useUserStore()
  const routerStore = useRouterStore()

  const token = userStore.token
  const isToLogin = to.name === 'Login'

  // 前往登录界面的
  if (isToLogin) {
    // 已登录
    if (token) {
      if (userStore.userInfo) {
        await getRouter()
        next({ name: 'Dashboard' })
      } else {
        userStore.clearInfo()
        next({ name: 'Login' })
      }
    } else {
      next()
    }
  } else {
    if (token) {
      const fromHome = from.path === '/'
      if (!isToLogin) {
        if (fromHome) {
          if (routerStore.routes.length <= 0) {
            await getRouter()
            next({ path: to.path })
          } else {
            next()
          }
        } else if (userStore.token) {
          next()
        } else {
          next({ name: 'Login', query: { redirect: to.path } })
        }
      } else {
        if (to.matched.length) {
          next()
        } else {
          next({ name: 'Login' })
        }
      }
    } else {
      next({ name: 'Login', replace: true })
    }
  }
})
