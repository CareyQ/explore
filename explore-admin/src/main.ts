import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import plugins from '@/plugins'
import './router/permission'

import 'element-plus/theme-chalk/src/message.scss'
import '@/assets/font/font.css'
import '@/assets/scss/element.scss'
import '@/assets/scss/common.scss'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(ElementPlus)
app.use(plugins)

app.mount('#app')
