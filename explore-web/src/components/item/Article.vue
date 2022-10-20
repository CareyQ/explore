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
      <div class="right">
        <p class="desc">{{ data.desc }}</p>
        <Meta
          :like="data.like"
          :location="data.location"
          :is-article="true"
          :comment="data.comment"
          :category="data.category"
        />
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
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
  margin: 1rem 0;
  white-space: pre-wrap;
  line-height: 1.5;
}

img {
  margin-right: 1rem;
  width: 150px;
  border-radius: 6px;
}

.right {
  flex: 1;
  position: relative;
}
</style>
