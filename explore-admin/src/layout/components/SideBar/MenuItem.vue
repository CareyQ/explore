<script setup lang="ts">
import { ElSubMenu, ElMenuItem } from 'element-plus'
import type { Menu } from '@/interface/Menu'
import type { RouteRecordRaw } from 'vue-router'

defineProps<{
  item: RouteRecordRaw
}>()
</script>

<template>
  <el-sub-menu v-if="item.children && item.children.length > 0" :index="item.path">
    <template #title>
      <i v-if="item.meta" :class="`icon-${item.meta.icon}`"></i>
      <span v-if="item.meta">{{ item.meta.title }}</span>
    </template>
    <div v-for="(child, index) of item.children" :key="index">
      <menu-item :item="child" />
    </div>
  </el-sub-menu>

  <el-menu-item v-else :index="item.path">
    <i v-if="item.meta" :class="'icon-' + item.meta.icon"></i>
    <template #title>
      <span v-if="item.meta">{{ item.meta.title }}</span>
    </template>
  </el-menu-item>
</template>

<style lang="scss" scoped>
i {
  margin-right: 16px;
}

.el-menu-item.is-active {
  color: var(--aside-text-color);
}
</style>

<style lang="scss">
.el-sub-menu .el-menu-item {
  padding-left: 52px !important;
}
</style>
