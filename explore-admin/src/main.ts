import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import 'element-plus/theme-chalk/index.css'
import './assets/styl/base.scss'

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')
