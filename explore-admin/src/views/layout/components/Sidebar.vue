<script setup lang="ts">
import { useRouteStore } from '@/stores/modules/route'
import MenuItem from './MenuItem.vue'

const routeStore = useRouteStore()
const router = useRouter()

const defaultRouter = ref(router.currentRoute.value.path)
const menus = computed(() => routeStore.routes)
console.log(router.options.routes[0].children)
</script>

<template>
  <aside class="sidebar">
    <div class="userinfo">
      <img
        class="avatar"
        src="https://lh3.googleusercontent.com/a-/AFdZucpjbUVr3flArvqxYRkyJPX7Zm5HXSq1veda0H6LYA=s96-c-rg-br100"
        alt=""
      />

      <div class="info">
        <h2>CareyQ</h2>
        <p>没放弃，留在这一天...</p>
      </div>
    </div>
    <hr />
    <el-scrollbar>
      <el-menu router :default-active="defaultRouter">
        <MenuItem :item="item" v-for="item in router.options.routes[0].children" :key="item.path" />
      </el-menu>
    </el-scrollbar>
  </aside>
</template>

<style lang="scss" scoped>
.sidebar {
  position: fixed;
  z-index: 1;
  top: 20px;
  left: 20px;
  height: calc(100vh - 40px);
  width: 250px;
  padding: 0 10px;
  border-radius: var(--border-radius);
  background-color: var(--white);
  box-shadow: var(--box-shadow);
  color: var(--text-dark-color);
}

.userinfo {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 32px 0 26px;
}

.avatar {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  border: 1px dashed var(--text-dark-color);
}

.info {
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

.el-scrollbar {
  height: calc(100vh - 180px);
}
</style>
