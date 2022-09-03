<script setup lang="ts">
import Content from './Content.vue'

const themeOverrides = {
  common: {
    primaryColor: '#01adb5'
  }
}
</script>

<template>
  <n-config-provider :theme-overrides="themeOverrides">
    <n-message-provider>
      <Content />
    </n-message-provider>
  </n-config-provider>
</template>
