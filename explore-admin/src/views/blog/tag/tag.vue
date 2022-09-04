<script setup lang="ts">
import { getTags, saveTag } from '@/service'
import { Icon } from '@iconify/vue'
import type { FormInst } from 'naive-ui'

const tags = ref<Tag[]>([])
const getData = async () => {
  const res = await getTags()
  if (res.code === 0 && res.data) {
    tags.value = res.data
  } else {
    window.$message.error(res.showMsg)
  }
}

onMounted(() => {
  getData()
})

const showModal = ref(false)
const form: Tag = reactive({
  id: '',
  name: '',
  alias: ''
})
const rules = {
  name: [{ required: true, message: '请输入标签名称', trigger: 'change' }],
  alias: [{ required: true, message: '请输入标签别名', trigger: 'change' }]
}
const formRef = ref<FormInst | null>(null)
const loading = ref(false)
const submit = () => {
  if (!formRef.value) {
    return
  }
  formRef.value.validate((errors) => {
    if (errors) {
      return
    }
    loading.value = true

    saveTag(form).then((res) => {
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

const handleEdit = (tag: Tag) => {
  if (tag) {
    form.id = tag.id
    form.name = tag.name
    form.alias = tag.alias
  }
  showModal.value = true
}

const reset = () => {
  showModal.value = false
  form.id = ''
  form.name = ''
  form.alias = ''
}
</script>

<template>
  <div class="tag">
    <n-card title="标签列表" :bordered="false">
      <n-space>
        <n-tag v-for="(item, index) in tags" :key="index" round closable type="primary" @click="handleEdit(item)">
          {{ item.name }}
        </n-tag>
        <n-button size="small" dashed @click="showModal = true">
          <template #icon>
            <Icon :icon="'ic:baseline-add'" />
          </template>
          添加
        </n-button>
      </n-space>
    </n-card>

    <n-modal v-model:show="showModal" preset="card" title="保存标签" closable style="width: 400px" @after-leave="reset">
      <n-form ref="formRef" :model="form" :rules="rules">
        <n-form-item label="名称" path="name">
          <n-input v-model:value="form.name" placeholder="标签名称" />
        </n-form-item>
        <n-form-item label="别名" path="alias">
          <n-input v-model:value="form.alias" placeholder="标签别名" />
        </n-form-item>
        <n-button type="primary" :loading="loading" @click="submit">保 存</n-button>
      </n-form>
    </n-modal>
  </div>
</template>

<style lang="scss" scoped>
.n-tag {
  cursor: pointer;
}
</style>
