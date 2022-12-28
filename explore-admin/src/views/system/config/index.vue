<script setup lang="ts">
import { getConfigPage } from '@/service'

const current = ref(1)
const size = ref(10)
const total = ref(0)

const getData = () => {
  getConfigPage(current.value, size.value).then((res) => {
    if (res.code === 0 && res.data) {
      const data = res.data
      total.value = data.total
      size.value = data.size
      current.value = data.current
    } else {
      ElMessage.error(res.showMsg)
    }
  })
}

onMounted(() => {
  getData()
})
</script>

<template>
  <el-card>
    <el-pagination
      small
      background
      v-model:current-page="current"
      :page-size="size"
      layout="prev, pager, next"
      :total="50"
    />
  </el-card>
</template>
