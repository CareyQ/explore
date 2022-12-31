<script lang="ts" setup>
import UserInfo from './UserInfo.vue'
import MenuItem from './MenuItem.vue'

const router = useRouter()
const defaultRouter = ref(router.currentRoute.value.path)
</script>

<template>
  <aside class="sidebar card">
    <UserInfo />
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
  top: 2rem;
  left: 2rem;

  height: calc(100vh - 4rem);
  width: 25rem;
  color: $dark-color;
}

.el-scrollbar {
  padding: 0 1rem;
  height: calc(100vh - 18rem);
}
</style>
