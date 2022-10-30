<script setup lang="ts">
import { link } from '@/mock/system'
</script>

<template>
  <div class="link page-content">
    <PageHeader :title="'友情链接'" :date="'2022-10-30'" :view="123" />

    <div class="content">
      <div class="link-item" v-for="(item, index) in link.friend" :key="index">
        <a href="" class="link-item-inner">
          <img :src="item.avatar" :alt="item.username" />
          <span class="name">{{ item.username }}</span>
        </a>
      </div>
    </div>

    <div class="invalid" v-if="link.invalid">
      <p>已失效链接</p>
      <ul>
        <li v-for="(item, index) in link.invalid" :key="index">
          <a href="">{{ item.username }}</a>
        </li>
      </ul>
    </div>

    <div class="submit-link">
      <h2 class="title">友链交换<span class="count">123</span></h2>
      <div class="form">
        <MyInput placeholder="网站名称（必填）" />
        <MyInput placeholder="网站地址（必填）" />
        <MyInput placeholder="网站图片（必填）" />
        <MyButton>提交</MyButton>
      </div>
    </div>

    <div class="link-record"></div>
  </div>
</template>

<style lang="scss" scoped>
.content {
  display: flex;
  flex-wrap: wrap;
  margin: 0 -8px;
}

.link-item {
  padding: 8px;
  width: 33.33333333%;
}

.link-item-inner {
  display: flex;
  align-items: center;
  padding: 12px 20px;
  box-shadow: 0 0 0 0.5px rgb(188 195 206 / 10%), 0 1px 10px rgb(48 55 66 / 6%);
  border-radius: 12px;
  transition: all 0.2s ease-in-out;
  img {
    margin-right: 1rem;
    width: 34px;
    height: 34px;
    border-radius: 4px;
  }
  &:hover {
    transform: translateY(-4px);
    box-shadow: 0 0 0 2px var(--primary), 0 2px 10px 1px rgba(51, 102, 255, 0.1);
  }
}

.info {
  display: flex;
  flex-direction: column;
  margin-left: 1rem;
  line-height: 1.5;
}

.form {
  display: flex;
  flex-direction: column;
  width: 40%;
}

.my-input {
  margin-bottom: 1rem;
}

.my-button {
  width: fit-content;
}

.title {
  margin-top: 2rem;
  margin-bottom: 0.5rem;
}

.count {
  margin-left: 4px;
  font-size: 0.875rem;
  opacity: 0.8;
}
</style>
