<script setup lang="ts">
import Content from './Content.vue'

const themeOverrides = {
  common: {
    primaryColor: '#01adb5',
    primaryColorHover: 'rgba(1, 174, 183, .8)',
    primaryColorPressed: '#03949b',
    borderFocus: '#01adb5'
  }
}
</script>

<template>
  <n-config-provider :theme-overrides="themeOverrides">
    <n-message-provider>
      <n-dialog-provider>
        <Content />
      </n-dialog-provider>
    </n-message-provider>
  </n-config-provider>
</template>
