<script setup lang="ts">
import { saveTag, getTags } from '@/api/tag'
import { ElCard, ElInput, ElMessage } from 'element-plus'
import { reactive, ref } from 'vue'
import type { Tag } from '@/interface/Blog'

const tags = ref<Tag[]>([])
const loading = ref(false)
const tagRef = ref()
const dialogFormVisible = ref(false)
const form: Tag = reactive({
  id: '',
  name: '',
  alias: ''
})
const rules = {
  name: [{ required: true, message: '请输入标签名称', trigger: 'change' }],
  alias: [{ required: true, message: '请输入标签别名', trigger: 'change' }]
}

const showDialog = (tag: Tag) => {
  if (tag) {
    form.id = tag.id
    form.name = tag.name
    form.alias = tag.alias
  }
  dialogFormVisible.value = true
}

const getList = () => {
  getTags().then((res) => {
    if (res.code === 0 && res.data) {
      tags.value = res.data
    } else {
      ElMessage.error(res.showMsg)
    }
  })
}
getList()

const submit = () => {
  tagRef.value.validate((valid: boolean) => {
    if (!valid) {
      return
    }
    loading.value = true
    saveTag(form).then((res) => {
      if (res.code === 0) {
        ElMessage.success(res.showMsg)
        closeDialog()
        getList()
      } else {
        ElMessage.error(res.showMsg)
      }
      loading.value = false
    })
  })
}

const closeDialog = () => {
  dialogFormVisible.value = false
  tagRef.value.resetFields()
}
</script>

<template>
  <div class="tag">
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>标签列表</span>
        </div>
      </template>

      <el-tag v-for="tag in tags" :key="tag" closable :disable-transitions="false" @click="showDialog(tag)">
        {{ tag.name }}
      </el-tag>
      <el-button class="button-new-tag" size="small" @click="showDialog"> + New Tag </el-button>
    </el-card>

    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>文章列表</span>
        </div>
      </template>

      articles
    </el-card>

    <el-dialog v-model="dialogFormVisible" title="保存标签" width="20%">
      <el-form ref="tagRef" :model="form" :rules="rules" label-position="top">
        <el-form-item label="名称" prop="name">
          <el-input v-model.trim="form.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="别名" prop="alias">
          <el-input v-model.trim="form.alias" autocomplete="off" />
        </el-form-item>
      </el-form>

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="closeDialog">取消</el-button>
          <el-button type="primary" @click="submit" :loading="loading">保存</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.el-tag {
  margin: 0 4px;
  cursor: pointer;
}

.el-button {
  margin-left: 4px;
}

.el-card {
  margin-bottom: 30px;
}
</style>
