<script setup lang="ts">
import { ElContainer, ElAside, ElMain } from 'element-plus'
import SideBar from './components/SideBar/SideBar.vue'
</script>

<template>
  <el-container>
    <el-aside width="256px">
      <SideBar />
    </el-aside>

    <el-main>
      <router-view v-slot="{ Component }">
        <transition mode="out-in" name="el-fade-in-linear">
          <component :is="Component" />
        </transition>
      </router-view>
    </el-main>
  </el-container>
</template>

<style lang="scss" scoped>
.el-aside {
  background-image: var(--bg-image);
  background-color: var(--main-color);
  color: var(--aside-text-color);
  &::-webkit-scrollbar {
    display: none;
  }
}
.el-container {
  height: 100%;
}
</style>
