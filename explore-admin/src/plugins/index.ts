import type { App } from 'vue'
import Editor from './mdEditor'

const pluginList = [Editor]

const plugins = {
  install(app: App<Element>) {
    Object.entries(pluginList).forEach(([, v]) => {
      app.use(v)
    })
  }
}

export default plugins
