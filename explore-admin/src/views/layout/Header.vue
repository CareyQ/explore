<script setup lang="ts">
import { computed } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const title = computed(() => {
  return router.currentRoute.value.meta.title
})
</script>

<template>
  <h2>{{ title }}</h2>
</template>

<style lang="scss" scoped>
h2 {
  padding: 30px 30px 20px;
  font-size: 24px;
}
</style>
