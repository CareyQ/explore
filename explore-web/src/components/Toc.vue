<script setup lang="ts">
const props = defineProps(['toc'])
</script>

<template>
  <ul>
    <li v-for="(item, index) in toc" :key="index">
      <a :href="'#' + item.id">{{ item.title }}</a>
      <Toc v-if="item.children && item.children.length > 0" :toc="item.children" />
    </li>
  </ul>
</template>

<style lang="scss" scoped>
.sidebar-toc {
  > ul {
    padding-left: 0;
  }
}
ul {
  font-size: 0.875rem;
  margin: 0;
  list-style: none;
  padding-left: 1rem;
  ul {
    a {
      padding: 4px 0;
    }
  }
}

a {
  display: block;
  padding: 8px 0;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}
</style>
