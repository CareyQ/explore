<script setup lang="ts">
const props = defineProps(['comments'])
const showForm = ref()

const replyHandle = (index: number, item) => {
  showForm.value = index
}
</script>

<template>
  <div class="comment-list">
    <div class="comment-group" v-for="(item, index) in comments" :key="index">
      <div class="root">
        <img :src="item.avatar" :alt="item.username" class="root-avatar" />
        <div class="root-content">
          <span class="username">{{ item.username }} </span>
          <div class="content">
            {{ item.comment }}
          </div>
          <div class="info">
            <span>来自云南省</span>
            <span>{{ item.date }}</span>
            <span @click="showForm = index">回复</span>
          </div>
        </div>
      </div>

      <CommentItem
        v-if="item.replies && item.replies.length > 0"
        :replies="item.replies"
        @reply="replyHandle(index, $event)"
      />
      <CommentForm v-if="showForm === index" />
    </div>
  </div>
</template>

<style lang="scss" scoped>
.comment-list {
  margin-top: 1rem;
}

.root {
  display: flex;
}

.root-avatar {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  margin-right: 1rem;
}

.root-content {
  flex: 1;
  padding-bottom: 1rem;
}

.comment-group {
  padding-top: 22px;
  padding-bottom: 1rem;
  border-top: 1px solid var(--gray);
  &:first-child {
    border-top: none;
  }
}
</style>

<style lang="scss">
.comment {
  .username {
    color: #61666d;
    font-size: 0.875rem;
    line-height: 30px;
  }

  .content {
    padding: 2px 0;
    line-height: 26px;
  }

  .info {
    display: flex;
    align-items: center;
    font-size: 13px;
    margin-top: 2px;
    color: #9499a0;
    > * {
      margin-right: 20px;
    }
  }
}
</style>
