<script setup lang="ts">
import { getCategories, saveCategory, saveTag, getTags } from '@/service'
import { ElMessage } from 'element-plus'

const categoryFormRef = ref()
const tagFromRef = ref()

const categoryForm = reactive<Category>({
  id: '',
  name: '',
  alias: '',
  hits: 0,
  sort: 0,
  count: 0
})
const tagForm: Tag = reactive({
  id: '',
  name: '',
  alias: ''
})

const rules = {
  name: [{ required: true, message: '请输入分类名称', trigger: 'change' }],
  alias: [{ required: true, message: '请输入分类别名', trigger: 'change' }]
}

const categoryLoading = ref(false)
const tagLoading = ref(false)

const categories = ref<Category[]>([])
const tags = ref<Tag[]>([])

const getData = async () => {
  getCategoryData()
  getTagData()
}

onMounted(() => {
  getData()
})

const getCategoryData = () => {
  getCategories().then((res) => {
    if (res.code === 0 && res.data) {
      categories.value = res.data
    } else {
      ElMessage.error(res.showMsg)
    }
  })
}

const getTagData = () => {
  getTags().then((res) => {
    if (res.code === 0 && res.data) {
      tags.value = res.data
    } else {
      ElMessage.error(res.showMsg)
    }
  })
}

const actionCell = (row: cellClassName) => {
  if (row.columnIndex === 5) {
    return 'table-action'
  }
}

const handleEditCategory = (category: Category) => {
  categoryForm.id = category.id as number
  categoryForm.name = category.name
  categoryForm.alias = category.alias
  categoryForm.sort = category.sort
}

const submitCategory = () => {
  categoryFormRef.value.validate((valid: boolean) => {
    if (!valid) {
      return
    }
    categoryLoading.value = true
    categoryForm.count = ''
    categoryForm.hits = ''
    saveCategory(categoryForm).then((res) => {
      if (res.code === 0) {
        ElMessage.success(res.showMsg)
        categoryFormRef.value.resetFields()
        categoryForm.sort = 0
        getCategoryData()
      } else {
        ElMessage.error(res.showMsg)
      }
      categoryLoading.value = false
    })
  })
}

const submitTag = () => {
  tagFromRef.value.validate((valid: boolean) => {
    if (!valid) {
      return
    }
    tagLoading.value = true
    saveTag(tagForm).then((res) => {
      if (res.code === 0) {
        ElMessage.success(res.showMsg)
        tagDialogVisible.value = false
        getTagData()
      } else {
        ElMessage.error(res.showMsg)
      }
      tagLoading.value = false
    })
  })
}

const resetCategory = () => {
  categoryFormRef.value.resetFields()
  categoryForm.sort = 0
}

const handleClose = () => {
  tagFromRef.value.resetFields()
}

const tagDialogVisible = ref(false)
const showTagDialog = (tag: Tag) => {
  tagDialogVisible.value = true
  nextTick(() => {
    if (tag) {
      tagForm.id = tag.id
      tagForm.name = tag.name
      tagForm.alias = tag.alias
    }
  })
}
</script>

<template>
  <div class="tag-category">
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
        @click="showTagDialog(tag)"
      >
        {{ tag.name }}
      </el-tag>
      <el-button class="button-new-tag" size="small" @click="tagDialogVisible = true"> + New Tag </el-button>
    </el-card>

    <el-row :gutter="20">
      <el-col :span="8">
        <el-card>
          <template #header>
            <h5>保存分类</h5>
          </template>

          <el-form ref="categoryFormRef" :model="categoryForm" :rules="rules" label-position="top">
            <el-form-item label="分类名称" prop="name">
              <el-input v-model.trim="categoryForm.name" placeholder="分类名称" />
            </el-form-item>

            <el-form-item label="分类别名" prop="alias">
              <el-input v-model.trim="categoryForm.alias" placeholder="页面路径标识，推荐英文" />
            </el-form-item>

            <el-form-item label="分类排序">
              <el-input-number v-model="categoryForm.sort" :min="0" controls-position="right" />
            </el-form-item>

            <el-form-item>
              <el-button type="primary" :loading="categoryLoading" @click="submitCategory">保 存</el-button>
              <el-button v-if="categoryForm.id || categoryForm.name || categoryForm.alias" @click="resetCategory">
                重 置
              </el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>

      <el-col :span="16">
        <el-card>
          <template #header>
            <h5>分类列表</h5>
          </template>

          <el-table :data="categories" style="width: 100%" :cell-class-name="actionCell">
            <el-table-column prop="name" label="名称" />
            <el-table-column prop="count" label="文章数" />
            <el-table-column prop="hits" label="点击数" />
            <el-table-column prop="sort" label="排序" />
            <el-table-column prop="alias" label="路径别名" />
            <el-table-column label="操作" class="table-action" width="80">
              <template #default="scope">
                <el-tooltip effect="dark" content="编辑分类" placement="top">
                  <el-icon class="icon-btn" :size="18" color="#8392ab" @click="handleEditCategory(scope.row)">
                    <FluentDocumentEdit24Filled class="icon" />
                  </el-icon>
                </el-tooltip>

                <el-tooltip effect="dark" content="删除分类" placement="top">
                  <el-icon class="icon-btn" :size="20" color="#8392ab">
                    <MdiDelete class="icon" />
                  </el-icon>
                </el-tooltip>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>

    <el-dialog v-model="tagDialogVisible" title="保存标签" width="25%" @closed="handleClose">
      <el-form ref="tagFromRef" :model="tagForm" :rules="rules" label-position="top">
        <el-form-item label="标签名称" prop="name">
          <el-input v-model="tagForm.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="标签别名" prop="alias">
          <el-input v-model="tagForm.alias" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="submitTag" :loading="tagLoading">保 存</el-button>
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

.button-new-tag {
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
