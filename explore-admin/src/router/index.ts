import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    redirect: '/login',
    meta: {
      requireAuth: false
    }
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/login/login.vue')
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'Other',
    redirect: ''
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
