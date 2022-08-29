<template>
  <el-config-provider>
    <RouterView />
  </el-config-provider>
</template>

<style lang="scss">
html,
body,
#app {
  height: 100vh;
}

body {
  font-family: -apple-system, BlinkMacSystemFont, Segoe UI, PingFang SC, Hiragino Sans GB, Microsoft YaHei,
    Helvetica Neue, Helvetica, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol;
}

#app {
  background-image: var(--bg-image);
  background-color: var(--body-color);
}
</style>
