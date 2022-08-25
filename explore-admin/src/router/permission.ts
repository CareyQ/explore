import { useUserStore } from '@/stores/modules/user'
import { useRouterStore } from '@/stores/modules/router'
import router from '@/router'
import type { RouteRecordName } from 'vue-router'

const whiteList: RouteRecordName[] = ['Login']

const getRouter = async () => {
  const routerStore = useRouterStore()
  await routerStore.asyncRouter()
  const asyncRouters = routerStore.routes
  console.log('加载路由')

  asyncRouters.forEach((asyncRouter) => {
    router.addRoute(asyncRouter)
  })
}

router.beforeEach(async (to, from, next) => {
  console.log('从' + from.path + '到' + to.path)
  console.log(router.getRoutes())

  const userStore = useUserStore()
  const routerStore = useRouterStore()
  const token = userStore.token
  const isToLogin = to.name === 'Login'

  // 前往登录界面的
  if (isToLogin) {
    // 已登录
    if (token) {
      console.log('去往登录，有 token')
      if (userStore.userInfo) {
        console.log('有用户')
        await getRouter()
        next({ name: 'Dashboard' })
      } else {
        console.log('没用户')
        userStore.ClearStorage()
        next({ name: 'Login' })
      }
    } else {
      console.log('直接去登录')
      next()
    }
  } else {
    // 前往其他界面，已登录
    if (token) {
      const fromHome = from.path === '/'
      if (!isToLogin) {
        if (fromHome) {
          console.log('来自 /')
          if (routerStore.routes.length <= 0) {
            await getRouter()
            next({ name: 'Dashboard', replace: true })
          } else {
            console.log('来自 / 没有加载路由')

            next()
          }
        } else if (userStore.token) {
          console.log('replace')
          next()
        } else {
          console.log('一登陆，去 login')
          next({ name: 'Login', query: { redirect: to.path } })
        }
      } else {
        if (to.matched.length) {
          next()
        } else {
          console.log('一登陆，去 login 222')
          next({ name: 'Login' })
        }
      }
    } else {
      console.log('去往其他，无 token')

      next({
        name: 'Login',
        replace: true
      })
      console.log('去往其他，无 token')
    }
  }
})
