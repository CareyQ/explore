<script setup lang="ts">
const props = defineProps({
  title: {
    type: String
  }
})

const active = inject('active')
</script>

<template>
  <div v-show="active === title" :data-title="title">
    <slot />
  </div>
</template>
