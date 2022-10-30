<script setup lang="ts"></script>

<template>
  <div class="form">
    <div class="user-info">
      <MyInput placeholder="邮箱（必填）" />
      <MyInput placeholder="昵称（必填）" />
      <MyInput placeholder="个人网站" />
    </div>

    <div class="content">
      <textarea class="text-box" placeholder="发表评论..." rows="3" />
    </div>

    <div class="handle">
      <div class="left">
        <span class="emoji">Emoji</span>
        <div class="check">
          <input type="checkbox" class="checkbox" />
          <span>私密评论</span>
        </div>
        <div class="check">
          <input type="checkbox" class="checkbox" />
          <span>邮件回复</span>
        </div>
      </div>

      <div class="right">
        <MyButton>发送</MyButton>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.user-info {
  display: flex;
  justify-content: space-between;
  margin: 0.5rem 0;
}

.my-input {
  width: 32%;
}

.content {
  textarea {
    width: 100%;
  }
}

.handle {
  padding: 0 4px;
  justify-content: space-between;
}

.handle,
.left {
  display: flex;
}

.left > * {
  margin-right: 1rem;
  cursor: pointer;
}

.comment-group .form {
  padding-left: 4rem;
}
</style>
