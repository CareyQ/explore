import { defineStore } from 'pinia'
import { getMenu } from '@/api/menu'
import type { Menu } from '@/interface/Menu'

export const useStore = defineStore('user', {
  state: () => ({
    menus: [] as Menu[]
  }),
  getters: {},
  actions: {
    setToken(token: any) {
      localStorage.setItem('token', token.toString())
    },
    async getMenus() {
      const data = await getMenu()
      this.menus = data.data as Menu[]
    }
  }
})
