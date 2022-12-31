<script lang="ts" setup>
import ExploreSidebar from './components/sidebar/ExploreSidebar.vue'
import ExploreHeader from './components/ExploreHeader.vue'
</script>

<template>
  <ExploreSidebar />
  <div class="hero"></div>
  <main class="main">
    <ExploreHeader />
    <router-view v-slot="{ Component }">
      <transition mode="out-in" name="el-fade-in-linear">
        <component :is="Component" />
      </transition>
    </router-view>
  </main>
</template>

<style lang="scss" scoped>
.hero {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 0;
  width: 100%;
  height: 300px;
  background-image: url(http://zhongguose.com/img/texture.png);
  background-color: $primary-color;
}

.main {
  position: relative;
  z-index: 1;
  margin-left: 27rem;
  padding: 2rem 2rem 0;
  height: calc(100vh - 2.5rem);
}
</style>
