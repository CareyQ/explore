<script setup lang="ts">
import { RouterView } from 'vue-router'
import { ElConfigProvider } from 'element-plus'
import { useStore } from '@/stores/index'

const store = useStore()

store.getMenus()
</script>

<template>
  <el-config-provider>
    <RouterView />
  </el-config-provider>
</template>

<style lang="scss">
html,
body,
#app {
  height: 100%;
}

#app {
  background-color: #edeff1;
}
</style>
