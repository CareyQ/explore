import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import './router/permission'
import plugins from '@/plugins'

import 'vfonts/Lato.css'
import 'vfonts/FiraCode.css'
import '@/assets/scss/common.scss'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(plugins)

app.mount('#app')
