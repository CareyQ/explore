import { defineStore } from 'pinia'
import { getMenu } from '@/api/menu'
import type { Menu } from '@/interface/Menu'
import type { LoginUser } from '@/interface/User'

export const useStore = defineStore('user', {
  state: () => ({
    menus: [] as Menu[]
  }),

  getters: {},

  actions: {
    /**
     * 登录返回的数据
     */
    loginData(loginUser: LoginUser) {
      localStorage.setItem('token', loginUser.token)
      localStorage.setItem('login_user', JSON.stringify(loginUser))
    },

    /**
     * 获取菜单
     */
    async getMenus() {
      const { data } = await getMenu()
      this.menus = data as Menu[]
    }
  }
})
