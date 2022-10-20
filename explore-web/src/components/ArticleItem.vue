<script setup lang="ts">
const props = defineProps<{
  data: ArticleItem
}>()

const isArticle = ref(props.data.type === 1)
</script>

<template>
  <div :class="['item', isArticle ? 'article' : 'moment']">
    <div class="header">
      <span class="type">{{ isArticle ? '文章' : '片刻' }}</span>
      <span class="date">{{ data.date }}</span>
    </div>

    <h2 class="title" v-if="isArticle">{{ data.title }}</h2>

    <div class="content">
      <div :class="['files', data.files.length > 1 && 'multiple']" v-if="data.files && data.files.length > 0">
        <img v-if="isArticle" :src="data.files[0]" :alt="data.title" />
        <div class="file-item" v-else>
          <div class="file-item-inner" v-for="(item, index) in data.files" :key="index">
            <img :src="item" />
          </div>
        </div>
      </div>

      <div class="detail">
        <p class="desc">{{ data.desc }}</p>
        <div class="meta">as</div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.title {
  margin-top: 1rem;
  color: var(--dark);
}

.content {
  display: flex;
  color: var(--dark);
  margin: 1rem 0;
}

.desc {
  white-space: pre-wrap;
  line-height: 1.5;
}

.item.article {
  .desc {
    flex: 1;
  }
  .files {
    margin-right: 1rem;
    img {
      width: 150px;
    }
  }
}

.files img {
  border-radius: 6px;
}

.item.moment {
  .content {
    flex-direction: column-reverse;
  }
  .files {
    img {
      width: 60%;
    }
  }
  .desc {
    margin-bottom: 1rem;
  }
}

.files.multiple {
  .file-item {
    margin: 0 -4px;
    display: flex;
    flex-wrap: wrap;
    flex-direction: row;
    align-content: center;
    width: 80%;
  }

  .file-item-inner {
    position: relative;
    padding: 4px;
    width: 25%;
    margin: 0 4px;
    img {
      position: absolute;
      top: 0;
      left: 0;
      bottom: 0;
      right: 0;
      width: 100%;
      height: 100%;
      object-fit: cover;
    }
    &:before {
      content: '';
      display: block;
      padding-top: 100%;
    }
  }
}
</style>
