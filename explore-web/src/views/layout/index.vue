<script setup lang="ts">
import Sidebar from './components/sidebar.vue'
import Hero from './components/hero.vue'
</script>

<template>
  <div class="container">
    <Sidebar />

    <main class="main">
      <Hero />
      <div class="page">
        <router-view v-slot="{ Component }">
          <transition mode="out-in" name="el-fade-in-linear">
            <component :is="Component" />
          </transition>
        </router-view>
      </div>
    </main>
  </div>
</template>

<style lang="scss" scoped>
.sidebar {
  position: relative;
  width: 15rem;
  margin-right: 1.25rem;
}

.main {
  flex: 1;
  border-radius: var(--border-radius);
  background-color: var(--white);
  box-shadow: var(--shadow);
}

.page {
  padding: 1.5rem 2.5rem 2.5rem;
}
</style>
