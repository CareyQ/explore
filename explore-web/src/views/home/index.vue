<script setup lang="ts">
import ArticleItem from '@/components/ArticleItem.vue'
import PageSide from '@/components/PageSide.vue'
import { articleList } from '@/mock/system'
</script>

<template>
  <div class="home aside">
    <div class="article-list">
      <ArticleItem v-for="(item, index) in articleList" :key="index" :data="item" />
    </div>
    <PageSide />
  </div>
</template>

<style lang="scss" scoped>
.aside {
  display: flex;
}
</style>
