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

    <span v-if="isArticle"># {{ category }}</span>
    <span v-else><uil:comment-alt-dots /> {{ comment }}</span>
  </div>
</template>

<style lang="scss" scoped>
.meta {
  position: absolute;
  bottom: 0;
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  font-size: 0.8125rem;
  background-color: rgba($color: #668cff, $alpha: 0.0987);
  border-radius: 8px;
  padding: 12px 10px;

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
</style>
