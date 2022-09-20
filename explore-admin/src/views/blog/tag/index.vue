<script setup lang="ts">
import { saveTag, getTags } from '@/service'
import { ElMessage } from 'element-plus'

const tags = ref<Tag[]>([])
const form: Tag = reactive({
  id: '',
  name: '',
  alias: ''
})
const getData = () => {
  getTags().then((res) => {
    if (res.code === 0 && res.data) {
      tags.value = res.data
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
  <div class="tag">
    <el-card>
      <template #header>
        <div class="card-header">
          <h5>标签列表</h5>
        </div>
      </template>

      <el-tag
        v-for="tag in tags"
        :key="tag"
        closable
        :disable-transitions="false"
        size="large"
        @click="showDialog(tag)"
      >
        {{ tag.name }}
      </el-tag>
      <el-button class="button-new-tag" size="small" @click="showDialog"> + New Tag </el-button>
    </el-card>

    <el-card>
      <template #header>
        <div class="card-header">
          <h5>文章列表</h5>
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

.el-button.button-new-tag {
  height: 32px !important;
  border-radius: 4px !important;
  padding-left: 1.25rem !important;
  padding-right: 1.25rem !important;
}

.el-card {
  margin-bottom: 30px;
}
</style>
