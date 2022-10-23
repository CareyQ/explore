<script setup lang="ts">
import { comment } from '@/mock/system'
</script>

<template>
  <h2>评论<span>123</span></h2>
  <div class="comment">
    <div class="form">
      <div class="info">
        <input type="text" placeholder="邮箱（必填）" />
        <input type="text" placeholder="昵称（必填）" />
        <input type="text" placeholder="个人网站" />
      </div>
    </div>

    <div class="content">
      <textarea placeholder="发表评论..." rows="4" />
      <div class="handle">
        <div class="left">
          <span>Emoji</span>
          <div class="private">
            <input type="checkbox" />
            私密评论
          </div>
          <div class="notice">
            <input type="checkbox" />
            邮件回复
          </div>
        </div>

        <div class="send">发送</div>
      </div>
    </div>

    <div class="comment-list">
      <div class="item" v-for="(item, index) in comment" :key="index">
        <div class="root">
          <div class="avatar">
            <img :src="item.avatar" :alt="item.username" />
          </div>
          <div class="comment-content">
            <div class="user">
              <span class="username">{{ item.username }} <span>云南省</span></span>
            </div>
            <div class="reply">
              {{ item.comment }}
            </div>
            <div class="reply-info">
              <span>{{ item.date }}</span>
              <span>回复</span>
            </div>
          </div>
        </div>

        <div class="sub-reply">
          <div class="reply-item" v-for="(reply, index) in item.replies" :key="index">
            <img :src="reply.avatar" alt="" />
            <span class="username"
              >{{ reply.replyUsername ? reply.username + ' 回复 @' + reply.replyUsername : reply.username }}
            </span>
            <span>{{ reply.comment }}</span>
            <div class="reply-info">
              <span>来自云南省</span>
              <span>{{ item.date }}</span>
              <span>回复</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
h2 {
  margin-top: 2rem;
  span {
    margin-left: 4px;
    font-size: 0.875rem;
    opacity: 0.8;
  }
}

.comment {
  margin: 1rem 0;
}

input,
textarea {
  padding: 6px 10px;
  border: 1px solid var(--gray);
  border-radius: 4px;
  font-size: 1rem;
  line-height: 1.5;
  &:focus {
    outline: none;
    border-color: var(--primary);
  }
}

.form {
  width: 100%;
  display: flex;
  align-items: center;
}

.info {
  display: flex;
  justify-content: space-between;
  width: 100%;
  input {
    width: 32%;
  }
}

.content {
  margin: 1rem 0;
  textarea {
    width: 100%;
    resize: vertical;
  }
}

.handle,
.left {
  display: flex;
  align-items: center;
}

.left > * {
  margin-right: 1rem;
}

.handle {
  padding: 0 0.5rem;
  justify-content: space-between;
  margin-top: 0.5rem;
  > * {
    cursor: pointer;
  }
}

.send {
  background-color: var(--primary);
  color: var(--white);
  padding: 4px 12px;
  border-radius: 4px;
}

.root {
  display: flex;
  .avatar {
    margin-right: 1rem;
    width: 48px;
    height: 48px;
    img {
      border-radius: 50%;
    }
  }
}

.username {
  color: #61666d;
  font-size: 0.875rem;
}

.user {
  margin-bottom: 4px;
  line-height: 30px;
}

.item {
  padding-top: 22px;
  padding-bottom: 1rem;
  border-top: 1px solid var(--gray);
  &:first-child {
    border-top: none;
  }
}

.reply {
  padding: 2px 0;
  line-height: 26px;
}

.reply-info {
  display: flex;
  align-items: center;
  margin-top: 2px;
  font-size: 13px;
  color: #9499a0;
  > * {
    margin-right: 20px;
  }
}

.comment-content {
  flex: 1;
  padding-bottom: 1rem;
}

.reply-item {
  position: relative;
  line-height: 26px;
  padding: 8px 0;

  img {
    position: absolute;
    left: -2.25rem;
    width: 30px;
    height: 30px;
    border-radius: 50%;
  }
}

.sub-reply {
  padding-left: 6.25rem;
  .username {
    margin-right: 0.975rem;
  }
}
</style>
