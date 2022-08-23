<script setup lang="ts">
import { ElContainer, ElAside, ElMain } from 'element-plus'
import SideBar from './components/SideBar/SideBar.vue'
</script>

<template>
  <el-container>
    <el-aside width="256px">
      <SideBar />
    </el-aside>

    <el-main> main </el-main>
  </el-container>
</template>

<style lang="scss" scoped>
.el-aside {
  background-color: $main-color;
  color: $aside-text-color;
}
.el-container {
  height: 100%;
}
</style>
