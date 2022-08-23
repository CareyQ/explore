<script setup lang="ts">
import { ElMenu } from 'element-plus'
import { useStore } from '@/stores/index'
import MenuItem from './MenuItem.vue'

const store = useStore()
</script>

<template>
  <div class="logo">Explore</div>

  <el-menu router>
    <MenuItem :item="item" v-for="item in store.menus" :key="item.id" />
  </el-menu>
</template>

<style lang="scss" scoped>
.logo {
  padding: 16px 0;
  font-size: 30px;
  font-weight: bold;
  color: var(--logo-color);
}

.el-menu {
  box-shadow: none;
  border-right: none;
}
</style>
