<script setup lang="ts"></script>

<template>
  <button class="my-button" type="button"><slot /></button>
</template>

<style lang="scss" scoped>
.my-button {
  background-color: var(--primary);
  color: var(--white);
  padding: 4px 12px;
  border-radius: 4px;
  cursor: pointer;
  border: none;
  font-size: 0.875rem;
}
</style>
