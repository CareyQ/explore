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
      <div class="map-item-inner">
        <div class="comment" v-if="item.comment > 0"></div>
        <div class="moment" v-if="item.moment > 0"></div>
        <div class="article" v-if="item.article > 0"></div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.commit-map {
  display: flex;
  flex-flow: wrap;
  margin: 0 -1px;
}

.map-item {
  position: relative;
  width: 6.666666666%;
  &::before {
    content: '';
    display: block;
    padding-top: 100%;
  }
  &::after {
    position: absolute;
    content: attr(tip);
    opacity: 0;
    font-size: 12px;
    background: rgba(48, 55, 66, 0.95);
    color: var(--white);
    white-space: pre;
    z-index: 9;
    padding: 4px 8px;
    border-radius: 4px;
    transform: translate(-50%, 8px);
    transition: opacity 0.2s, transform 0.2s;
    bottom: 100%;
    left: 50%;
    max-width: 320px;
    display: block;
    pointer-events: none;
  }
  &:hover {
    &::after {
      opacity: 1;
      transform: translate(-50%, -4px);
    }
  }
}

.map-item-inner {
  position: absolute;
  top: 1px;
  bottom: 1px;
  left: 1px;
  right: 1px;
  background-color: rgba(188, 195, 206, 0.1);
  display: flex;
  flex-direction: column;
  border-radius: 0.1rem;
  overflow: hidden;
  box-shadow: 0 0 0 1px rgb(188 195 206 / 15%) inset;
}

.comment {
  background-color: rgba(188, 195, 206, 0.4);
  flex: 1;
}

.article {
  flex: 1;
  background-color: #668cff;
}

.moment {
  flex: 1;
  background-color: #4dce5e;
}
</style>
