import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import routes from './routers'
import Layout from '@/layout/index.vue'
import { useStore } from '@/stores/index'
import { generateRouter } from '@/utils/menu'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

router.beforeEach(async (to, from, next) => {
  const token = localStorage.getItem('token')
  const store = useStore()

  if (token && to.name !== 'login') {
    if (store.menus.length === 0) {
      await store.getMenus()
      const menus = generateRouter(store.menus)
      console.log(store.menus)

      const layout = routes.find(
        (itme: RouteRecordRaw) => itme.name == 'Layout'
      )
      console.log(layout)

      if (layout) {
        layout.redirect = menus[0].path
        layout.children = [...menus]
        router.addRoute(layout)
      }
    }
  }

  next()
})

export default router
