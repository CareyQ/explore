<script setup lang="ts">
const router = useRouter()
const routers = computed(() => {
  const routes = router.currentRoute.value.matched
  routes.forEach((e) => {
    if (e.name === 'Layout') {
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
  padding: 1rem 1rem 3.5rem;
  color: $white-color;
}

.el-breadcrumb {
  margin-bottom: 1.5rem;
  font-size: 1.6rem;
}
</style>

<style lang="scss">
.el-breadcrumb__inner.is-link {
  font-weight: normal;
  &:hover {
    opacity: 0.7;
  }
}

.el-breadcrumb__inner,
.el-breadcrumb__inner.is-link,
.el-breadcrumb__item:last-child .el-breadcrumb__inner {
  color: $white-color;
  &:hover {
    color: inherit;
  }
}
</style>
