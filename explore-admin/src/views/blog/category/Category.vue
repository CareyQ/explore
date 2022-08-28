<script setup lang="ts">
import { ElCard, ElMessage } from 'element-plus'
import { reactive, ref, toRefs } from 'vue'
import { saveCategory, getCategories, exchange } from '@/api/category'
import CategoryList from '@/components/category/categoryList.vue'
import type { Category, CategoryData } from '@/interface/Category'

const categoryRef = ref()
const data: CategoryData = reactive({
  readOnly: true,
  loading: false,
  form: {
    id: '',
    name: '',
    alias: '',
    hits: 0,
    sort: 0
  },
  categories: []
})

const { readOnly, form, categories } = toRefs(data)

const rules = {
  name: [{ required: true, message: '请输入分类名称', trigger: 'change' }],
  alias: [{ required: true, message: '请输入分类别名', trigger: 'change' }]
}

const submit = () => {
  categoryRef.value.validate((valid: boolean) => {
    if (!valid) {
      return
    }
    data.loading = true
    saveCategory(data.form).then((res) => {
      console.log(res)

      if (res.code === 0) {
        ElMessage.success(res.showMsg)
        categoryRef.value.resetFields()
        form.value.sort = 0
        getList()
      } else {
        ElMessage.error(res.showMsg)
      }
      data.loading = false
    })
  })
}

const cancelReadOnly = () => {
  data.readOnly = false
}

const getList = () => {
  getCategories().then((res) => {
    if (res.code === 0 && res.data) {
      data.categories = res.data
    } else {
      ElMessage.error(res.showMsg)
    }
  })
}
getList()

const onExchange = (ids: number[]) => {
  exchange(ids).then((res) => {
    if (res.code === 0) {
      ElMessage.success(res.showMsg)
    } else {
      ElMessage.error(res.showMsg)
    }
    getList()
  })
}

const onEdit = (category: Category) => {
  data.form.id = category.id
  data.form.name = category.name
  data.form.alias = category.alias
  data.form.sort = category.sort
}
</script>

<template>
  <el-row :gutter="30">
    <el-col :span="8">
      <el-card shadow="never">
        <template #header>
          <div class="card-header">
            <span>保存分类</span>
          </div>
        </template>

        <div class="category-form">
          <el-form label-position="top" :model="form" :rules="rules" ref="categoryRef">
            <el-form-item label="名称" prop="name">
              <el-input
                v-model.trim="form.name"
                placeholder="分类名称"
                :readonly="readOnly"
                @focus="cancelReadOnly()"
              />
            </el-form-item>

            <el-form-item label="别名" prop="alias">
              <el-input
                v-model.trim="form.alias"
                placeholder="页面路径标识，推荐英文"
                :readonly="readOnly"
                @focus="cancelReadOnly()"
              />
            </el-form-item>

            <el-form-item label="排序">
              <el-input-number v-model="form.sort" :min="0" :max="10" />
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="submit" :loading="data.loading">保 存</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-card>
    </el-col>

    <el-col :span="16">
      <el-card shadow="never">
        <template #header>
          <div class="card-header">
            <span>分类列表</span>
          </div>
        </template>

        <CategoryList :list="categories" @exchangeId="onExchange" @edit="onEdit" />
      </el-card>
    </el-col>
  </el-row>
</template>

<style lang="scss" scoped>
.el-select {
  width: 100%;
}
</style>
