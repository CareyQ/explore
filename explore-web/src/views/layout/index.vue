<script setup lang="ts">
import Sidebar from './components/sidebar.vue'
</script>

<template>
  <div class="container">
    <Sidebar />

    <main class="main">
      <router-view v-slot="{ Component }">
        <transition mode="out-in" name="el-fade-in-linear">
          <component :is="Component" />
        </transition>
      </router-view>
    </main>
  </div>
</template>

<style lang="scss" scoped>
.sidebar {
  width: 15rem;
  margin-right: 1.25rem;
}

.main {
  flex: 1;
}
</style>
