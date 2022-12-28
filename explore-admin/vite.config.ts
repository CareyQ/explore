import path from "path";
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import Icons from 'unplugin-icons/vite'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

const IconsResolver = require('unplugin-icons/resolver')

export default defineConfig({
  resolve: {
    alias: {
      "@/": `${path.resolve(__dirname, "src")}/`
    }
  },
  css: {
    preprocessorOptions: {
      scss: {
        prependData: `@use "element-plus/theme-chalk/src/index.scss" as *;`,
        additionalData: `@use "@/assets/scss/var.scss" as *;`
      }
    }
  },
  plugins: [
    vue(),
    Icons({ compiler: 'vue3', autoInstall: true }),
    AutoImport({
      include: [/\.[tj]sx?$/, /\.vue$/, /\.vue\?vue/, /\.md$/],
      dts: './auto-imports.d.ts',
      resolvers: [ElementPlusResolver()],
      imports: ['vue', 'vue-router', 'pinia']
    }),
    Components({
      resolvers: [ElementPlusResolver({ importStyle: 'sass' }), IconsResolver({ prefix: false })]
    })
  ]
})
