<script setup lang="ts">
const props = defineProps<{
  location: string
  like: number
  isArticle: boolean
  comment: number
  category: string
}>()
</script>

<template>
  <div class="meta">
    <div>
      <span><material-symbols:location-on-outline-rounded class="icon" /> {{ location }}</span>
      <span><majesticons:heart-line class="icon" /> {{ like }}</span>
    </div>

    <span v-if="isArticle" class="category"># {{ category }}</span>
    <span class="comment" v-else><uil:comment-alt-dots class="comment-icon" /> {{ comment }}</span>
  </div>
</template>

<style lang="scss" scoped>
.meta {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  font-size: 0.8125rem;
  background-color: #f5f5f7;
  border-radius: 8px;
  padding: 10px;
  margin: 8px 0 1rem;
  span {
    display: flex;
    align-items: flex-end;
    color: var(--text-color);
    margin-right: 24px;
  }

  > div {
    display: flex;
  }
}

.icon {
  margin-right: 4px;
  color: var(--text-gray);
}

span.comment {
  cursor: pointer;
  padding: 4px 8px;
  border-radius: 4px;
  color: var(--white);
  background-color: var(--primary);
}

.comment-icon {
  margin-right: 4px;
}

.category {
  cursor: pointer;
  padding: 4px 8px;
}
</style>
