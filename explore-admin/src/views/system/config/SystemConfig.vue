<script setup lang="ts">
import type { Config } from '@/model'
const { proxy } = getCurrentInstance()!

const state = reactive({
  tableData: [] as Config[]
})

const { tableData } = toRefs(state)

const getConfigs = async () => {
  const { data } = await proxy.$axios.get('/config/page', {
    params: {
      current: 1,
      size: 10
    }
  })
  state.tableData = data.records
}

getConfigs()
</script>

<template>
  <el-card class="page-body">
    <template #header>
      <el-button type="primary">添加配置</el-button>
    </template>

    <el-table :data="tableData" style="width: 100%">
      <el-table-column type="selection" width="55" />
      <el-table-column prop="code" label="配置 Code" width="200" />
      <el-table-column prop="value" label="配置值" width="300" />
      <el-table-column prop="configDesc" label="配置说明" />

      <el-table-column label="操作" class="table-action" width="300">
        <template #default="scope">
          <el-button link type="primary" size="small" @click="">编辑</el-button>
          <el-button link type="danger" size="small" @click="">删除</el-button>
        </template>
      </el-table-column>

      <template #empty>
        <el-empty :image-size="200" />
      </template>
    </el-table>

    <el-pagination small background :page-size="10" layout="prev, pager, next" :total="50" />
  </el-card>
</template>
