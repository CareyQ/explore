import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import routes from './routers'
import Layout from '@/layout/index.vue'
import { useStore } from '@/stores/index'
import { generateRouter } from '@/utils/menu'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

const TOKEN = 'token'
const LOGIN = 'Login'

router.beforeEach(async (to, from, next) => {
  const token = localStorage.getItem(TOKEN)
  const store = useStore()
  console.log(to)
  const isToLogin = to.name === LOGIN

  if (token) {
    if (isToLogin) {
      next({ name: 'Layout' })
    } else {
      next()
    }
  } else {
    if (isToLogin) {
      next()
    } else {
      // 没 token 且不是去登录页的，全部重定向的登录页
      next({ name: LOGIN })
    }
  }

  // if (to.name !== 'login' && !token) {
  //   next({ name: 'Login' })
  // } else if (to.name !== 'login') {
  //   if (store.menus.length === 0) {
  //     await store.getMenus()
  //     const menus = generateRouter(store.menus)
  //     console.log(store.menus)

  //     const layout = routes.find((itme: RouteRecordRaw) => itme.name == 'Layout')
  //     console.log(layout)

  //     if (layout) {
  //       layout.redirect = menus[0].path
  //       layout.children = [...menus]
  //       router.addRoute(layout)
  //     }
  //   }
  //   next()
  // }
})

export default router
