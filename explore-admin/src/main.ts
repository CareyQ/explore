import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import './router/permission'

import { useUserStore } from '@/stores/modules/user'
import cookie from 'js-cookie'
import plugins from '@/plugins'

// import 'element-plus/theme-chalk/index.css'
import './assets/styl/var.scss'
import './assets/styl/base.scss'

const app = createApp(App)

app.use(router)
app.use(createPinia())
app.use(plugins)

const userStore = useUserStore()
if (!userStore.token) {
  const token = cookie.get('token')
  if (token) {
    userStore.token = token
  }
}

app.mount('#app')
