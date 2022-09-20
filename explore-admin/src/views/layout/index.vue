<script setup lang="ts">
import Sidebar from './components/Sidebar.vue'
import Header from './components/Header.vue'
</script>

<template>
  <div class="container">
    <Sidebar />
    <div class="header-bg"></div>
    <main class="main">
      <Header />
      <router-view v-slot="{ Component }">
        <transition mode="out-in" name="el-fade-in-linear">
          <component :is="Component" />
        </transition>
      </router-view>
    </main>
  </div>
</template>

<style lang="scss" scoped>
.header-bg {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 0;
  width: 100%;
  height: 300px;
  background-image: url(http://zhongguose.com/img/texture.png);
  background-color: var(--text-dark-color);
}

.main {
  padding: 20px 20px 0;
  margin-left: 270px;
  position: relative;
  height: calc(100vh - 25px);
  z-index: 1;
}
</style>
