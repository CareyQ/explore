<script setup lang="ts">
import { ElMenu } from 'element-plus'
import { useStore } from '@/stores/index'
import MenuItem from './MenuItem.vue'

const store = useStore()
</script>

<template>
  <div class="sidebar">
    <div class="logo">Explore</div>

    <el-menu router>
      <div class="menus">
        <div class="menu-item" v-for="item in store.menus" :key="item.id">
          <MenuItem :item="item" />
        </div>
      </div>
    </el-menu>
  </div>
</template>

<style lang="scss" scoped>
.logo {
  padding: 16px 0;
  text-align: center;
  font-size: 24px;
  font-weight: bold;
  background-color: $sub-color;
  box-shadow: 10px 10px 20px rgb(33 44 55 / 30%);
}

.el-menu {
  box-shadow: none;
  border-right: none;
}
</style>
