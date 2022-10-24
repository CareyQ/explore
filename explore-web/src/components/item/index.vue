<script setup lang="ts">
import Article from './Article.vue'
import Moment from './Moment.vue'

const props = defineProps<{
  data: ArticleItem
}>()

const isArticle = ref(props.data.type === 1)
</script>

<template>
  <div :class="['item', isArticle ? 'article' : 'moment']">
    <div class="header">
      <span class="tag tag-gray">{{ isArticle ? '文章' : '片刻' }}</span>
      <span class="date">{{ data.date }}</span>
    </div>

    <Article v-if="isArticle" :data="data" />
    <Moment v-else :data="data" />
  </div>
</template>

<style lang="scss" scoped>
.item {
  margin-bottom: 2rem;
  border-bottom: 1px solid var(--gray);
  &:last-child {
    border-bottom: none;
  }
}

.header {
  display: flex;
  align-items: center;
  color: var(--text-gray);
  font-size: 0.875rem;
  span {
    margin-right: 1rem;
  }
}
</style>
