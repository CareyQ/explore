<script setup lang="ts">
const props = defineProps(['title', 'date', 'view', 'category'])
</script>

<template>
  <div class="page-header">
    <h1 class="page-title">{{ title }}</h1>
    <div class="meta" v-if="date || view || category">
      <span><mdi:calendar-week-begin v-if="date" />{{ date }}</span>
      <span><akar-icons:statistic-up v-if="view" />{{ view }}</span>
      <span v-if="category"># {{ category }}</span>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.page-header {
  margin-bottom: 2rem;
  padding-bottom: 0.5rem;
  border-bottom: 1px solid var(--gray);
}
.meta,
.meta span {
  display: flex;
  align-items: center;
}

.meta {
  margin-top: 6px;
  opacity: 0.7;
  span {
    margin-right: 1.5rem;
    svg {
      margin-right: 6px;
    }
  }
}
</style>
