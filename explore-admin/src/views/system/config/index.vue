<script setup lang="ts">
import { getConfigPage, saveConfig, delConfig } from '@/service'

const current = ref(1)
const size = ref(10)
const total = ref(0)
const dialogVisible = ref(false)
const formRef = ref()
const saveLoading = ref(false)
const configs = ref<Config[]>([])

const rules = {
  code: [{ required: true, message: '请输入 Code', trigger: 'change' }],
  value: [{ required: true, message: '请输入配置值', trigger: 'change' }]
}

const initData: Config = {
  id: '',
  code: '',
  value: '',
  configDesc: ''
}

const configForm = ref<Config>({ ...initData })

const getData = () => {
  getConfigPage(current.value, size.value).then((res) => {
    if (res.code === 0 && res.data) {
      const data = res.data
      total.value = data.total
      size.value = data.size
      current.value = data.current
      configs.value = data.records
    } else {
      ElMessage.error(res.showMsg)
    }
  })
}

onMounted(() => {
  getData()
})

const editClick = (config: Config) => {
  configForm.value = { ...config }
  dialogVisible.value = true
}

const submitConfig = () => {
  formRef.value.validate((valid: boolean) => {
    if (!valid) {
      return
    }
    saveLoading.value = true
    saveConfig(configForm.value).then((res) => {
      if (res.code === 0) {
        ElMessage.success(res.showMsg)
        getData()
        dialogVisible.value = false
      } else {
        ElMessage.error(res.showMsg)
      }
      saveLoading.value = false
    })
  })
}

const handleClose = () => {
  formRef.value.resetFields()
  configForm.value = initData
}

const delClick = (id: number) => {
  delConfig(id).then((res) => {
    if (res.code === 0) {
      ElMessage.success(res.showMsg)
      getData()
    } else {
      ElMessage.error(res.showMsg)
    }
  })
}

const onChange = (val: number) => {
  current.value = val
  getData()
}
</script>

<template>
  <div>
    <el-card>
      <div class="page-header">
        <el-button type="primary" @click="dialogVisible = true">添加配置</el-button>
      </div>

      <div class="config-body">
        <el-table :data="configs" style="width: 100%">
          <el-table-column type="selection" width="55" />
          <el-table-column prop="code" label="Code" width="200" />
          <el-table-column prop="value" label="值" width="300" />
          <el-table-column prop="configDesc" label="配置说明" />

          <el-table-column label="操作" class="table-action" width="300">
            <template #default="scope">
              <el-button link type="primary" size="small" @click="editClick(scope.row)">编辑</el-button>
              <el-button link type="danger" size="small" @click="delClick(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>

          <template #empty>
            <el-empty :image-size="200" />
          </template>
        </el-table>
      </div>

      <el-pagination
        small
        background
        v-model:current-page="current"
        :page-size="size"
        layout="prev, pager, next"
        :total="total"
        @current-change="onChange"
      />
    </el-card>

    <el-dialog v-model="dialogVisible" title="保存配置" width="25%" @closed="handleClose">
      <el-form ref="formRef" :model="configForm" :rules="rules" label-position="top">
        <el-form-item label="Code" prop="code">
          <el-input v-model="configForm.code" autocomplete="off" />
        </el-form-item>
        <el-form-item label="值" prop="value">
          <el-input v-model="configForm.value" autocomplete="off" />
        </el-form-item>
        <el-form-item label="说明" prop="configDesc">
          <el-input v-model="configForm.configDesc" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="submitConfig" :loading="saveLoading">保 存</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
