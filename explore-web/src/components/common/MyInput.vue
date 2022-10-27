<script setup lang="ts">
const props = defineProps(['modelValue', 'placeholder'])
</script>

<template>
  <input
    class="text-box my-input"
    ref="input"
    :placeholder="placeholder"
    type="text"
    :value="modelValue"
    @input="$emit('update:modelValue', ($event.target as HTMLInputElement).value)"
  />
</template>
