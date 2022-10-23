<script setup lang="ts">
const props = defineProps<{
  data: ArticleItem
}>()
</script>

<template>
  <div class="moment">
    <p class="desc">{{ data.desc }}</p>

    <div v-if="data.files && data.files.length > 0" :class="['files', data.files.length > 1 && 'multiple']">
      <div class="file-item" v-for="(item, index) in data.files" :key="index">
        <img :src="item" />
      </div>
    </div>

    <Meta
      :like="data.like"
      :location="data.location"
      :is-article="false"
      :comment="data.comment"
      :category="data.category"
    />
  </div>
</template>

<style lang="scss" scoped>
.moment {
  position: relative;
  margin-bottom: 1rem;
}

.list .moment {
  border-bottom: 1px solid var(--gray);
  width: 90%;
  &:last-child {
    border-bottom: none;
  }
}

.desc {
  color: var(--dark);
  margin: 1rem 0;
  white-space: pre-wrap;
  line-height: 1.5;
  font-size: 1.125rem;
  text-align: justify;
}

.files {
  margin: 0 -4px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  align-content: center;
  width: 80%;
  img {
    border-radius: 6px;
    width: 60%;
  }
}

.files.multiple .file-item {
  position: relative;
  padding: 4px;
  width: 25%;
  margin: 0 4px 8px;
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
</style>
