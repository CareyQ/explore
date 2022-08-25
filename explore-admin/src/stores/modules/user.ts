import { defineStore } from 'pinia'
import { login } from '@/api/login'
import type { LoginUser, LoginInfo } from '@/interface/User'
import { ref, watch } from 'vue'
import { useRouterStore } from './router'
import router from '@/router/index'

export const useUserStore = defineStore('user', () => {
  const token = ref(window.localStorage.getItem('token') || '')
  const userInfo = ref({
    name: '',
    avatar: ''
  })

  const setToken = (val: string) => {
    token.value = val
  }

  const setUserInfo = (val: LoginUser) => {
    userInfo.value = val
  }

  /**
   *
   * @param loginInfo 登录
   * @returns
   */
  const Login = async (loginInfo: LoginInfo) => {
    const { data } = await login(loginInfo)

    if (data) {
      setUserInfo(data)
      setToken(data.token)

      const routerStore = useRouterStore()
      await routerStore.asyncRouter()
      const asyncRouters = routerStore.routes
      asyncRouters.forEach((item) => {
        router.addRoute(item)
      })
      await router.push({ name: 'Dashboard' })
    }
  }

  /**
   * 清除登陆数据
   */
  const ClearStorage = async () => {
    token.value = ''
    sessionStorage.clear()
    localStorage.clear()
  }

  watch(
    () => token.value,
    () => {
      window.localStorage.setItem('token', token.value)
    }
  )

  return {
    Login,
    token,
    userInfo,
    setToken,
    setUserInfo,
    ClearStorage
  }
})
