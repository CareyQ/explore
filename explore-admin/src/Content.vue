<script setup lang="ts">
import { useMessage } from 'naive-ui'

onMounted(() => {
  window.$message = useMessage()
})
</script>
<template>
  <RouterView />
</template>

<style lang="scss"></style>
