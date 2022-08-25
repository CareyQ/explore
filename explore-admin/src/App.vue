<script setup lang="ts">
import { ElConfigProvider } from 'element-plus'
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
  background-image: var(--bg-image);
  background-color: var(--body-color);
}
</style>
