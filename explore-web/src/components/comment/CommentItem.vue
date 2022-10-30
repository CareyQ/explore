<script setup lang="ts">
const props = defineProps(['replies'])
</script>

<template>
  <div class="replies">
    <div class="reply content" v-for="(item, index) in replies" :key="index">
      <img :src="item.avatar" :alt="item.username" />

      <span class="username">
        {{ item.replyUsername ? item.username + ' 回复 @' + item.replyUsername : item.username }}
      </span>
      <span>{{ item.comment }}</span>

      <div class="info">
        <span>来自云南省</span>
        <span>{{ item.date }}</span>
        <span @click="$emit('reply', item)">回复</span>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.replies {
  padding-left: 6.25rem;
}

.reply.content {
  position: relative;
  padding: 8px 0;
}

img {
  position: absolute;
  left: -2.25rem;
  width: 30px;
  height: 30px;
  border-radius: 50%;
}
</style>
