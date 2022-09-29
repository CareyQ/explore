<script setup lang="ts">
import { useRouteStore } from '@/stores/modules/route'

const routeStore = useRouteStore()
const menus = computed(() => routeStore.menus)
</script>

<template>
  <aside class="sidebar">
    <div class="menu">
      <div class="card" v-for="(items, index) in menus" :key="index">
        <div class="link" v-for="item in items" :key="item.path">
          <router-link v-if="item && item.meta" :to="item.path">
            <component :is="item.meta.icon" class="icon" />
            <span>{{ item.meta.title }}</span>
          </router-link>
        </div>
      </div>
    </div>
  </aside>
</template>

<style lang="scss" scoped>
.sidebar {
  font-size: 1rem;
}

.link {
  padding: 10px;
  a {
    display: flex;
    align-items: center;
  }
  .icon {
    margin-right: 6px;
    font-size: 1.125rem;
  }
}
</style>
