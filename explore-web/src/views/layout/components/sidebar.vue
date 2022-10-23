<script setup lang="ts">
import { useRouteStore } from '@/stores/modules/route'

const routeStore = useRouteStore()
const menus = computed(() => routeStore.menus)
</script>

<template>
  <aside class="sidebar">
    <div class="sidebar-content">
      <div class="card" v-for="(items, index) in menus" :key="index">
        <div class="link" v-for="item in items" :key="item.path">
          <router-link v-if="item && item.meta && item.meta.show" :to="item.path">
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

.sidebar-content {
  position: sticky;
  top: 1rem;
}

.link {
  margin-bottom: 4px;
  a {
    display: flex;
    align-items: center;
    padding: 8px 10px;
    border-radius: 8px;
    &:hover {
      background-color: #eaedf1;
    }
  }
  .icon {
    margin-right: 8px;
    font-size: 1.125rem;
  }
}

a.router-link-active.router-link-exact-active {
  background-color: var(--primary);
  color: var(--white);
}
</style>
