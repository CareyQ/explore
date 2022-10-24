<script setup lang="ts">
import { archive } from '@/mock/system'
</script>

<template>
  <div class="detail">
    <div class="module category">
      <h3 class="title">博客分类</h3>
      <div class="wrap">
        <div v-for="(item, index) in archive.categories" :key="index" class="item">
          <a href="">{{ item.name }}({{ item.count }})</a>
        </div>
      </div>
    </div>

    <div class="module blog-tag">
      <h3 class="title">博客标签</h3>
      <div class="wrap">
        <div v-for="(item, index) in archive.tags" :key="index" class="tag">
          <a href="">{{ item.name }}</a>
        </div>
      </div>
    </div>

    <div class="module article">
      <h3 class="title">博客文章</h3>
      <div class="wrap">
        <div v-for="(item, index) in archive.articles" :key="index" class="article-item">
          <div class="year">
            {{ item.year }} <span>{{ item.data.length }}</span>
          </div>
          <div class="data">
            <div class="article" v-for="(article, index) in item.data" :key="index">
              {{ article.name }} - {{ article.date }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.module {
  margin-bottom: 2rem;
}

.title {
  margin-bottom: 0.5rem;
}

.wrap {
  margin: 0 -8px;
  display: flex;
}

.category {
  a {
    border-bottom: 1px solid var(--dark);
  }
  .item {
    padding: 4px 8px;
  }
}

.blog-tag {
  a {
    padding: 2px 8px;
    border: 1px solid var(--text-gray);
    background-color: #fafafa;
    font-size: 0.875rem;
    border-radius: 4px;
    color: var(--text-gray);
  }
  .item {
    padding: 4px;
  }
}

.article .wrap {
  flex-direction: column;
  margin: 0;
  margin-left: 0.75rem;
}

.year {
  margin: 1.5rem 0;
  font-size: 1.25rem;
  span {
    font-size: 0.875rem;
    opacity: 0.8;
  }
  &::before {
    position: absolute;
    left: -8px;
    top: 30px;
    content: '';
    width: 18px;
    height: 18px;
    background-color: var(--white);
    display: block;
    border-radius: 50%;
    border: 3px solid var(--primary);
  }
}

.article-item {
  &:first-child {
    .year {
      margin-top: 0;
      &::before {
        top: 5px;
      }
    }
  }
}

.article-item {
  position: relative;
  padding-left: 2rem;
  &::before {
    position: absolute;
    left: 0;
    top: 0.5rem;
    content: '';
    height: 100%;
    width: 2px;
    display: block;
    background-color: var(--gray);
  }
}

.article {
  line-height: 1.6;
  margin-bottom: 1rem;
}
</style>
