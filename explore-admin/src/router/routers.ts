import type { RouteRecordRaw } from 'vue-router'
import Layout from '@/layout/index.vue'
const Routers: RouteRecordRaw[] = [
  {
    path: '/',
    name: 'Layout',
    redirect: '',
    component: Layout,
    children: []
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/Login.vue')
  }
]

export default Routers
