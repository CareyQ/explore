<script setup lang="ts">
const router = useRouter()
const routers = computed(() => {
  const routes = router.currentRoute.value.matched
  routes.forEach((e) => {
    if (e.meta.title === 'Layout') {
      e.meta.title = '首页'
    }
  })
  return routes
})

const title = computed(() => {
  return router.currentRoute.value.meta.title
})
</script>

<template>
  <div class="header">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item>
        <el-icon :size="16">
          <IconParkSolidApplicationOne class="icon" />
        </el-icon>
      </el-breadcrumb-item>
      <el-breadcrumb-item v-for="(item, index) in routers" :key="index" :to="{ path: item?.path }">
        {{ item.meta.title }}
      </el-breadcrumb-item>
    </el-breadcrumb>

    <span>{{ title }}</span>
  </div>
</template>

<style lang="scss" scoped>
.header {
  padding: 10px 10px 36px;
  color: #fff;
}

.el-breadcrumb {
  margin-bottom: 14px;
}
</style>
