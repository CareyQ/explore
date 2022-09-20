<script setup lang="ts">
import { getAttachmentCategoryList, saveAttachmentCategory } from '@/service'
import { ElMessage } from 'element-plus'

const categories = ref<AttachmentCategory[]>([])
const categoryForm = reactive({
  id: '',
  name: '',
  categoryDesc: '',
  password: '',
  isShow: true
})
const dialogFormVisible = ref(false)

const getData = () => {
  getAttachmentCategoryList().then((res) => {
    if (res.code === 0 && res.data) {
      categories.value = res.data
    } else {
      ElMessage.error(res.showMsg)
    }
  })
}

onMounted(() => {
  getData()
})

const saveCategory = () => {}
</script>

<template>
  <div class="file">
    <el-dialog v-model="dialogFormVisible" title="保存文件分类" width="30%">
      <el-form ref="tagRef" :model="categoryForm" :rules="rules" label-position="top">
        <el-form-item prop="name">
          <el-input placeholder="分类名称" />
        </el-form-item>
        <el-form-item prop="password">
          <el-input placeholder="访问密码" />
        </el-form-item>
        <el-form-item>
          <el-select placeholder="是否显示" clearable>
            <el-option label="否" value="0" />
            <el-option label="是" value="1" />
          </el-select>
        </el-form-item>
      </el-form>

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="closeDialog">取消</el-button>
          <el-button type="primary" @click="submit" :loading="loading">保存</el-button>
        </span>
      </template>
    </el-dialog>

    <el-card>
      <div class="filter">
        <el-form :inline="true">
          <el-form-item prop="categoryId">
            <el-select placeholder="文件类型" clearable> </el-select>
          </el-form-item>
          <el-form-item prop="tagId">
            <el-select placeholder="请选择标签" clearable> </el-select>
          </el-form-item>
          <el-form-item prop="status">
            <el-select placeholder="请选择状态" clearable>
              <el-option label="草稿" value="0" />
              <el-option label="已发布" value="1" />
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary">查 询</el-button>
            <el-button>重 置</el-button>
          </el-form-item>
        </el-form>

        <el-button type="primary">
          <el-icon class="icon-btn" :size="18">
            <UisUploadAlt class="icon" />
          </el-icon>
          上 传
        </el-button>
        <el-button type="primary">
          <el-icon class="icon-btn" :size="18">
            <IcBaselineChecklistRtl class="icon" />
          </el-icon>
          全 选
        </el-button>
        <el-button>
          <el-icon class="icon-btn" :size="18">
            <MdiDelete class="icon" />
          </el-icon>
          删 除
        </el-button>

        <el-tabs tab-position="left" style="height: 200px" type="border-card">
          <el-tab-pane :label="item.name" v-for="(item, index) in categories" :key="index">User</el-tab-pane>
        </el-tabs>
      </div>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.el-card {
  margin-bottom: 30px;
}

.save-category {
  .el-form-item {
    margin-bottom: 0 !important;
  }
}

.el-tabs--border-card {
  margin-top: 24px;
  border-radius: 8px;
}
</style>

<style lang="scss">
.el-tabs--border-card > .el-tabs__header {
  border-bottom: none;
}
</style>
