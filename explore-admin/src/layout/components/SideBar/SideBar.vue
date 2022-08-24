<script setup lang="ts">
import { ElMenu } from 'element-plus'
import { useStore } from '@/stores/index'
import MenuItem from './MenuItem.vue'

const store = useStore()
</script>

<template>
  <div class="user">
    <img class="avatar" src="http://127.0.0.1/avatar/1512021413c8df5ebc2161f2391c9030.jpg" alt="" />

    <div class="user-info">
      <h2>CareyQ</h2>
      <p>没放弃，留在这一天...</p>
    </div>
  </div>

  <el-menu router>
    <MenuItem :item="item" v-for="item in store.menus" :key="item.id" />
  </el-menu>
</template>

<style lang="scss" scoped>
.user {
  padding: 30px 20px 20px;
  display: flex;
  align-items: center;
}

.avatar {
  width: 46px;
  height: 46px;
  border-radius: 50%;
  border: 2px solid #fff;
}

.user-info {
  margin-left: 14px;
  line-height: 1;
  h2 {
    margin-bottom: 10px;
    font-size: 20px;
    font-weight: bold;
  }
  p {
    margin: 0;
    font-size: 12px;
    opacity: 0.8;
  }
}

.el-menu {
  box-shadow: none;
  border-right: none;
}
</style>
