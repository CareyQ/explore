<script setup lang="ts">
import { ElSubMenu, ElMenuItem } from 'element-plus'
import type { Menu } from '@/interface/Menu'
defineProps<{
  item: Menu
}>()
</script>

<template>
  <!-- 多级菜单 -->
  <el-sub-menu
    v-if="item.children && item.children.length > 0"
    :index="item.route"
  >
    <template #title>
      <i :class="`icon-${item.icon}`"></i>
      <span>{{ item.title }}</span>
    </template>
    <el-menu-item
      v-for="(child, index) in item.children"
      :key="index"
      :route="item.route"
    >
      <template #title>{{ child.title }}</template>
    </el-menu-item>
  </el-sub-menu>

  <!-- 单级菜单 -->
  <el-menu-item v-else :index="item.route" :route="item.route">
    <i :class="'icon-' + item.icon"></i>
    <template #title>{{ item.title }}</template>
  </el-menu-item>
</template>

<style lang="scss" scoped>
i {
  margin-right: 16px;
}
</style>
