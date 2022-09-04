import type { App } from 'vue'
import Editor from './md-editor'

const plugins = {
  install(app: App<Element>) {
    app.use(Editor)
  }
}

export default plugins
