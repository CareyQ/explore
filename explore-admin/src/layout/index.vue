<script setup lang="ts">
import { ElContainer, ElAside, ElMain } from 'element-plus'
import SideBar from './components/SideBar/SideBar.vue'
import { useStore } from '@/stores/index'

const store = useStore()
store.getMenus()
</script>

<template>
  <el-container>
    <el-aside width="256px">
      <SideBar />
    </el-aside>

    <el-main>
      <RouterView />
    </el-main>
  </el-container>
</template>

<style lang="scss" scoped>
.el-aside {
  background-image: var(--bg-image);
  background-color: $main-color;
  color: $aside-text-color;
  &::-webkit-scrollbar {
    display: none;
  }
}
.el-container {
  height: 100%;
}
</style>
