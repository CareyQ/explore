<script setup lang="ts">
import type { Res, Config } from '@/model'
import { ElMessage, type FormInstance } from 'element-plus'
import axios from '@/util/axios'
import { toArray } from '@/util'

const tableData = ref<Config[]>([])
const current = ref(1)
const size = ref(10)
const total = ref(0)
const dialogVisible = ref(false)
const formRef = ref<FormInstance>()
const saveBtnLoading = ref(false)
const showBatchDelBtn = ref(false)
const batchDelIds = ref<number[]>([])

const config: Config = reactive({
  id: '',
  code: '',
  value: '',
  configDesc: ''
})

const rules = {
  code: [{ required: true, message: '请输入 Code', trigger: 'change' }],
  value: [{ required: true, message: '请输入配置值', trigger: 'change' }]
}

const getConfigs = async () => {
  const { data } = await axios.get('/config/page', {
    params: {
      current: current.value,
      size: size.value
    }
  })
  tableData.value = data.records
  current.value = data.current
  size.value = data.size
  total.value = data.total
}

const onChange = () => {
  getConfigs()
}

const onClose = () => {
  formRef.value?.resetFields()
  config.id = ''
}

const saveConfig = () => {
  formRef.value?.validate(async (valid: boolean) => {
    if (!valid) {
      return
    }
    saveBtnLoading.value = true
    const res = (await axios.post('/config/save', config)) as Res

    if (res.code === 0) {
      ElMessage.success(res.showMsg)
      getConfigs()
      dialogVisible.value = false
    }
    saveBtnLoading.value = false
  })
}

const editConfig = (data: Config) => {
  dialogVisible.value = true
  nextTick(() => {
    Object.assign(config, data)
  })
}

const delConfig = async (delIds: number[]) => {
  const res = (await axios.delete('/config/del', { data: delIds })) as Res
  if (res.code === 0) {
    ElMessage.success(res.showMsg)
    getConfigs()
  }
}

const handleSelection = (val: Config[]) => {
  if (val.length > 0) {
    showBatchDelBtn.value = true
    batchDelIds.value = val.map((e) => {
      return Number(e.id)
    })
  } else {
    showBatchDelBtn.value = false
    batchDelIds.value = []
  }
}

getConfigs()
</script>

<template>
  <div>
    <el-card class="page-body">
      <template #header>
        <el-button type="primary" @click="dialogVisible = true">添加配置</el-button>
        <el-button v-if="showBatchDelBtn" type="danger" @click="delConfig(batchDelIds)">批量删除</el-button>
      </template>

      <el-table :data="tableData" style="width: 100%" @selection-change="handleSelection">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="code" label="配置 Code" width="200" />
        <el-table-column prop="value" label="配置值" width="400" />
        <el-table-column prop="configDesc" label="配置说明" />

        <el-table-column label="操作" class="table-action" width="300">
          <template #default="scope">
            <el-button link type="primary" size="small" @click="editConfig(scope.row)">编辑</el-button>
            <el-popconfirm title="是否确定删除该条配置？" :width="200" @confirm="delConfig(toArray(scope.row.id))">
              <template #reference>
                <el-button link type="danger" size="small">删除</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>

        <template #empty>
          <el-empty :image-size="200" />
        </template>
      </el-table>

      <el-pagination
        small
        background
        layout="prev, pager, next"
        v-model:current-page="current"
        v-model:page-size="size"
        :total="total"
        @current-change="onChange"
      />
    </el-card>

    <el-dialog v-model="dialogVisible" title="保存配置" width="25%" @closed="onClose">
      <el-form ref="formRef" :model="config" :rules="rules" label-position="top">
        <el-form-item label="配置 Code" prop="code">
          <el-input v-model="config.code" autocomplete="off" />
        </el-form-item>
        <el-form-item label="配置值" prop="value">
          <el-input v-model="config.value" autocomplete="off" />
        </el-form-item>
        <el-form-item label="配置说明" prop="configDesc">
          <el-input v-model="config.configDesc" autocomplete="off" />
        </el-form-item>
      </el-form>

      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="saveConfig" :loading="saveBtnLoading">保 存</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
