<script setup lang="ts">
import type { Res, Resource } from '@/model'
import axios from '@/util/axios'

const props = defineProps({
  id: {
    type: Number
  },
  visible: {
    type: Boolean
  }
})
const emit = defineEmits(['visible'])

const detailVisible = computed({
  get() {
    return props.visible
  },
  set(value) {
    emit('visible', value)
  }
})

const resource = ref<Resource>()
const getResourceDetail = async () => {
  if (!props.id) {
    return
  }
  const { data } = await axios.get(`/resource/detail?id=${props.id}`)
  resource.value = data
}

const openHandle = () => {
  getResourceDetail()
}
</script>

<template>
  <el-dialog
    v-model="detailVisible"
    width="50%"
    @closed=""
    @open="openHandle"
    :show-close="false"
    class="resouce-detail"
  >
    <template #header>
      <div class="header">
        <span>{{ '附件：' + resource?.name }}</span>
        <div class="right">
          <el-icon :size="20"><ic:outline-navigate-before class="icon" /></el-icon>
          <el-icon :size="20"><ic:outline-navigate-next class="icon" /></el-icon>
          <el-icon :size="20"><ic:baseline-close class="icon" /></el-icon>
        </div>
      </div>
    </template>

    <div class="preview">
      <img v-if="resource?.type === 'image'" :src="resource?.path" :alt="resource.name" />
      <span v-else>该文件暂不支持预览</span>
    </div>

    <el-descriptions title="User Info">
      <el-descriptions-item label="Username">kooriookami</el-descriptions-item>
      <el-descriptions-item label="Telephone">18100000000</el-descriptions-item>
      <el-descriptions-item label="Place">Suzhou</el-descriptions-item>
      <el-descriptions-item label="Remarks">
        <el-tag size="small">School</el-tag>
      </el-descriptions-item>
      <el-descriptions-item label="Address"
        >No.1188, Wuzhong Avenue, Wuzhong District, Suzhou, Jiangsu Province</el-descriptions-item
      >
    </el-descriptions>
  </el-dialog>
</template>

<style lang="scss" scoped>
.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.right {
  margin-right: -2rem;
  .el-icon {
    cursor: pointer;
    margin-left: 1rem;
  }
}

.preview {
  margin: 0 -2rem 2rem;
  padding: 2rem 0;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  border: 1px solid $border-color;
  background-color: $bg-color;
  img {
    border-radius: 4px;
    height: 180px;
  }
}
</style>

<style lang="scss">
.resouce-detail .el-dialog__body {
  padding-top: 0;
}
</style>
