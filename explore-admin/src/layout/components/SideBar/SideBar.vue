<script setup lang="ts">
import { ElMenu } from 'element-plus'
import { useRouterStore } from '@/stores/modules/router'
import MenuItem from './MenuItem.vue'
import { useRouter } from 'vue-router'
import { ref } from 'vue'

const router = useRouter()
const defaultRouter = ref(router.currentRoute.value.path)

const routerStore = useRouterStore()
</script>

<template>
  <div class="user">
    <img
      class="avatar"
      src="https://lh3.googleusercontent.com/a-/AFdZucpjbUVr3flArvqxYRkyJPX7Zm5HXSq1veda0H6LYA=s96-c-rg-br100"
      alt=""
    />

    <div class="user-info">
      <h2>CareyQ</h2>
      <p>没放弃，留在这一天...</p>
    </div>
  </div>

  <el-menu router :default-active="defaultRouter">
    <MenuItem :item="item" v-for="item in routerStore.routes[0].children" :key="item.path" />
  </el-menu>
</template>

<style lang="scss" scoped>
.user {
  padding: 30px 20px;
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
