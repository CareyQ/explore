<script setup lang="ts">
import TagList from '@/components/TagList.vue'
import { monmentTags, article } from '@/mock/system'
</script>

<template>
  <div class="moment">
    <TagList :data="monmentTags" :type="1" />
    <div class="list">
      <Article v-for="(item, index) in article" :key="index" :data="item" />
    </div>
  </div>
</template>
