<script setup lang="ts">
import { articleDetail } from '@/mock/system'
import Comment from '../../components/Comment.vue'

const route = useRoute()
</script>

<template>
  <div class="detail">
    <div class="header">
      <h2 class="title">{{ articleDetail.title }}</h2>
      <p class="meta">
        <span><mdi:calendar-week-begin />{{ articleDetail.date }}</span>
        <span><akar-icons:statistic-up />{{ articleDetail.view }}</span>
        <span># {{ articleDetail.categoryName }}</span>
      </p>
    </div>

    <div class="content article-content" v-html="articleDetail.content"></div>

    <div class="footer">
      <div class="tags">
        <span v-for="(item, index) in articleDetail.tags" :key="index">{{ item.name }}</span>
      </div>
      <div class="nav">
        <div><entypo:triangle-left /> {{ articleDetail.prev.title }}</div>
        <div>{{ articleDetail.next.title }} <entypo:triangle-right /></div>
      </div>
    </div>

    <Comment />
  </div>
</template>

<style lang="scss" scoped>
.title {
  font-size: 1.875rem;
}

.meta {
  margin-top: 6px;
  opacity: 0.8;
}

.meta,
.nav,
.nav > div,
.meta span {
  display: flex;
  align-items: center;
}

.meta span {
  margin-right: 1.5rem;
  svg {
    margin-right: 6px;
  }
}

.header {
  margin-bottom: 2rem;
  padding-bottom: 1rem;
  border-bottom: 1px solid var(--gray);
}

.content {
  font-size: 1.125rem;
  line-height: 1.6;
  text-align: justify;
}

.footer {
}

.tags {
  padding-bottom: 1rem;
  border-bottom: 1px solid var(--gray);
  span {
    margin-right: 6px;
    padding: 4px 10px;
    background-color: var(--dark);
    color: var(--white);
  }
}

.nav {
  margin: 1rem 0;
  justify-content: space-between;
  font-size: 1.125rem;
}
</style>

<style lang="scss">
.article-content {
  p {
    margin-bottom: 1rem;
  }
}
</style>
