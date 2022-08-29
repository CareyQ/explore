<script setup lang="ts">
import SideBar from './components/sideBar/SideBar.vue'
import Title from './components/main/Title.vue'
</script>

<template>
  <el-container>
    <el-aside width="256px">
      <SideBar />
    </el-aside>

    <el-main>
      <Title />
      <router-view v-slot="{ Component }">
        <transition mode="out-in" name="el-fade-in-linear">
          <component :is="Component" style="height: calc(100% - 58px)" />
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

.el-main {
  padding: 40px;
}
</style>
