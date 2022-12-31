<script setup lang="ts">
import type { RouteRecordRaw } from 'vue-router'

defineProps<{
  item: RouteRecordRaw
}>()
</script>

<template>
  <el-sub-menu v-if="item.children && item.children.length > 0" :index="item.path">
    <template #title>
      <component v-if="item.meta" :is="item.meta.icon" />
      <span v-if="item.meta">{{ item.meta.title }}</span>
    </template>

    <menu-item :item="child" v-for="(child, index) of item.children" :key="index" />
  </el-sub-menu>

  <el-menu-item v-else :index="item.path">
    <component v-if="item.meta" :is="item.meta.icon" />
    <template #title>
      <span v-if="item.meta">{{ item.meta.title }}</span>
    </template>
  </el-menu-item>
</template>
