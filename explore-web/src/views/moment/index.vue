<script setup lang="ts">
import TagList from '@/components/TagList.vue'
import { monmentTags, moment } from '@/mock/system'
</script>

<template>
  <div class="moment">
    <TagList :data="monmentTags" :type="1" />
    <div class="list">
      <Moment v-for="(item, index) in moment" :key="index" :data="item" />
    </div>
  </div>
</template>
