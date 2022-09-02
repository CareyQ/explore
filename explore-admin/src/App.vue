<script setup lang="ts">
import Content from './Content.vue'
</script>

<template>
  <n-config-provider>
    <n-message-provider>
      <Content />
    </n-message-provider>
  </n-config-provider>
</template>
