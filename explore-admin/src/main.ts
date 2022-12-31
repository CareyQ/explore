import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import axois from '@/util/axios'

import 'element-plus/theme-chalk/src/message.scss'
import '@/assets/style/element.scss'
import '@/assets/font/font.css'
import '@/assets/style/base.scss'

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.config.globalProperties.$axios = axois

app.mount('#app')
