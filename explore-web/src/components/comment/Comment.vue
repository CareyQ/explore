<script setup lang="ts">
import { comment } from '@/mock/system'
</script>

<template>
  <div class="comment">
    <h2 class="title">评论<span class="count">123</span></h2>
    <CommentForm />
    <CommentList v-if="comment" :comments="comment" />
  </div>
</template>

<style lang="scss" scoped>
.title {
  margin-top: 2rem;
}

.count {
  margin-left: 4px;
  font-size: 0.875rem;
  opacity: 0.8;
}
</style>
