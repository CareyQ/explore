import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import Icons from 'unplugin-icons/vite'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'

const IconsResolver = require('unplugin-icons/resolver')

export default defineConfig({
  plugins: [
    vue(),
    Icons({ compiler: 'vue3', autoInstall: true }),
    AutoImport({
      include: [/\.[tj]sx?$/, /\.vue$/, /\.vue\?vue/, /\.md$/],
      dts: './auto-imports.d.ts',
      imports: ['vue', 'vue-router', 'pinia']
    }),
    Components({
      resolvers: [IconsResolver({ prefix: false })]
    })
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})
