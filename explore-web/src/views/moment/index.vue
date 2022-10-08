<script setup lang="ts">
import TagList from '@/components/TagList.vue'
import { monmentTags } from '@/mock/system'
</script>

<template>
  <div class="moment">
    <TagList :data="monmentTags" :type="1" />
    moment
  </div>
</template>
