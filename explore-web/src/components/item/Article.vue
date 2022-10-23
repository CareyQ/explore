<script setup lang="ts">
import Meta from './Meta.vue'

const props = defineProps<{
  data: ArticleItem
}>()
</script>

<template>
  <div class="article">
    <h2 class="title">{{ data.title }}</h2>

    <div class="content">
      <img v-if="data.files.length > 0" :src="data.files[0]" :alt="data.title" />
      <p class="desc">{{ data.desc }}</p>
    </div>

    <Meta
      :like="data.like"
      :location="data.location"
      :is-article="true"
      :comment="data.comment"
      :category="data.category"
    />
  </div>
</template>

<style lang="scss" scoped>
.article {
  margin-bottom: 1rem;
}

.list .article {
  border-bottom: 1px solid var(--gray);
  &:last-child {
    border-bottom: none;
  }
  img {
    width: 20%;
  }
}

.title {
  margin-top: 1rem;
  color: var(--dark);
}

.content {
  display: flex;
  color: var(--dark);
  margin: 1rem 0;
}

.desc {
  color: var(--dark);
  white-space: pre-wrap;
  line-height: 1.5;
  font-size: 1.125rem;
  text-align: justify;
}

img {
  margin-right: 1rem;
  width: 30%;
  border-radius: 6px;
}
</style>
