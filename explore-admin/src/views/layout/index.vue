<script setup lang="ts">
import Sidebar from './Sidebar.vue'
import Header from './Header.vue'
</script>

<template>
  <div class="container">
    <Sidebar />

    <div class="content">
      <Header />
      <main class="main">
        <router-view v-slot="{ Component }">
          <transition mode="out-in" name="el-fade-in-linear">
            <component :is="Component" />
          </transition>
        </router-view>
      </main>
    </div>
  </div>
</template>
