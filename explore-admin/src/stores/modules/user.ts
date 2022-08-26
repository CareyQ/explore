import { defineStore } from 'pinia'
import { login } from '@/api/login'
import type { LoginInfo } from '@/interface/User'
import { useRouterStore } from './router'
import router from '@/router/index'
import cookie from 'js-cookie'

const TOKEN = 'token'

export const useUserStore = defineStore({
  id: 'user',
  state: () => {
    return {
      token: '',
      userInfo: {}
    }
  },

  getters: {},
  actions: {
    /**
     *
     * @param loginInfo 登录
     * @returns
     */
    async Login(loginInfo: LoginInfo) {
      const { data } = await login(loginInfo)

      if (data) {
        this.userInfo = data
        this.token = data.token
        cookie.set(TOKEN, this.token)

        const routerStore = useRouterStore()
        await routerStore.asyncRouter()
        const asyncRouters = routerStore.routes
        asyncRouters.forEach((item) => {
          router.addRoute(item)
        })
        await router.push({ name: 'Dashboard' })
      }
    },

    async clearInfo() {
      this.token = ''
      this.userInfo = {}
      cookie.remove(TOKEN)
    }
  }
})
