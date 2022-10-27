<script setup lang="ts">
import { link } from '@/mock/system'
const something = ref('')
console.log(something.value)
</script>

<template>
  <div class="link">
    <div class="content">
      <div class="link-item" v-for="(item, index) in link.friend" :key="index">
        <a href="" class="link-item-inner">
          <img :src="item.avatar" :alt="item.username" />
          <div class="info">
            <span class="name">{{ item.username }}</span>
            <span class="desc" v-if="item.desc">{{ item.desc }}</span>
          </div>
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
      <div class="form">
        <MyInput placeholder="网站名称（必填）" />
        <MyInput placeholder="网站地址（必填）" />
        <MyInput placeholder="网站图片（必填）" />
        <MyInput placeholder="网站描述" />
      </div>
    </div>
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
  padding: 20px;
  box-shadow: 0 0 0 0.5px rgb(188 195 206 / 10%), 0 1px 10px rgb(48 55 66 / 6%);
  border-radius: 6px;
  transition: all 0.2s ease-in-out;
  img {
    width: 48px;
    height: 48px;
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

.name {
  color: var(--primary);
}

.desc {
  font-size: 0.875rem;
  color: var(--text-gray);
}

.my-input {
  display: block;
}
</style>
