<script setup lang="ts">
const props = defineProps<{
  commitMap: Commit[]
}>()

const buildTip = (item: Commit) => {
  let res = item.date
  if (item.comment > 0) {
    res += '\n评论：' + item.comment
  }
  if (item.moment > 0) {
    res += '\n片刻：' + item.moment
  }
  if (item.article > 0) {
    res += '\n文章：' + item.article
  }
  return res
}
</script>

<template>
  <div class="commit-map">
    <div class="map-item" v-for="(item, index) in commitMap" :key="index" :tip="buildTip(item)">
      <div class="comment" v-if="item.comment > 0">1</div>
      <div class="moment" v-if="item.moment > 0">2</div>
      <div class="article" v-if="item.article > 0">3</div>
    </div>
  </div>
</template>
