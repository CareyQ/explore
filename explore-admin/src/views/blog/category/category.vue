<script setup lang="ts">
import { NButton, NSpace } from 'naive-ui'
import type { FormInst, DataTableColumn } from 'naive-ui'
import { getCategories, saveCategory } from '@/service'

const rules = {
  name: [{ required: true, message: '请输入分类名称', trigger: 'change' }],
  alias: [{ required: true, message: '请输入分类别名', trigger: 'change' }]
}
const form = reactive<Category>({
  id: '',
  name: '',
  alias: '',
  hits: 0,
  sort: 0,
  count: 0
})
const formRef = ref<FormInst | null>(null)
const loading = ref(false)

const columns: DataTableColumn[] = [
  { title: '名称', key: 'name' },
  { title: '文章数', key: 'count' },
  { title: '点击数', key: 'hits' },
  { title: '排序', key: 'sort' },
  { title: '路径别名', key: 'alias' },
  {
    title: '操作',
    key: 'action',
    render: (row) => {
      return [
        h(
          NButton,
          {
            strong: true,
            tertiary: true,
            size: 'small',
            type: 'primary',
            style: 'margin-right: 15px;',
            onClick: () => handleEdit(row as Category)
          },
          { default: () => '编辑' }
        ),
        h(
          NButton,
          {
            strong: true,
            tertiary: true,
            size: 'small',
            type: 'error'
          },
          { default: () => '删除' }
        )
      ]
    }
  }
]

const handleEdit = (category: Category) => {
  form.id = category.id as number
  form.name = category.name
  form.alias = category.alias
  form.sort = category.sort
}

const categories = ref<Category[]>([])
const getData = async () => {
  const res = await getCategories()
  if (res.code === 0 && res.data) {
    categories.value = res.data
  } else {
    window.$message.error(res.showMsg)
  }
}

onMounted(() => {
  getData()
})

const reset = () => {
  form.id = ''
  form.name = ''
  form.alias = ''
  form.sort = 0
}

const submit = () => {
  if (!formRef.value) {
    return
  }
  formRef.value.validate((errors) => {
    if (errors) {
      return
    }
    loading.value = true
    form.count = ''
    form.hits = ''
    saveCategory(form).then((res) => {
      if (res.code === 0) {
        window.$message.success(res.showMsg)
        reset()
        getData()
      } else {
        window.$message.error(res.showMsg)
      }
      loading.value = false
    })
  })
}
</script>

<template>
  <n-grid x-gap="20" cols="6">
    <n-grid-item span="2">
      <n-card title="保存分类" :bordered="false">
        <n-form ref="formRef" :model="form" :rules="rules">
          <n-form-item label="名称" path="name">
            <n-input v-model:value="form.name" placeholder="分类名称" />
          </n-form-item>
          <n-form-item label="别名" path="alias">
            <n-input v-model:value="form.alias" placeholder="页面路径标识，推荐英文" />
          </n-form-item>
          <n-form-item label="排序">
            <n-input-number v-model:value="form.sort" :default-value="0" min="0" />
          </n-form-item>
          <n-form-item>
            <n-space>
              <n-button type="primary" :loading="loading" @click="submit">保 存</n-button>
              <n-button v-if="form.name || form.alias" @click="reset">重 置</n-button>
            </n-space>
          </n-form-item>
        </n-form>
      </n-card>
    </n-grid-item>

    <n-grid-item span="4">
      <n-card title="分类列表" :bordered="false">
        <n-data-table :columns="columns" :data="categories" :bordered="false" />
      </n-card>
    </n-grid-item>
  </n-grid>
</template>
