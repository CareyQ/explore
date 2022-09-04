<script setup lang="ts">
import defaultImg from '@/assets/img/placeholder.jpg'
import { getCategories, saveCategory } from '@/service'
import type { SelectOption } from 'naive-ui'

const form = reactive<Article>({
  id: '',
  alias: '',
  title: '',
  categoryId: '',
  summary: '',
  allowComment: true,
  isTop: false,
  isOriginal: true,
  author: '',
  source: '',
  password: '',
  thumbnail: '',
  seoKeywords: '',
  seoDesc: '',
  tags: [],
  content: '',
  original: ''
})

const categoryOptions = ref<SelectOption[]>([])
const getData = () => {
  getCategories().then((res) => {
    if (res.code === 0 && res.data) {
      res.data.forEach((e) => {
        const option = {
          label: e.name,
          value: e.id
        }
        categoryOptions.value.push(option)
      })
    } else {
      window.$message.error(res.showMsg)
    }
  })
}

onMounted(() => {
  getData()
})

const rules = {
  title: [{ required: true, message: '请输入文章标题', trigger: 'change' }],
  alias: [{ required: true, message: '请输入文章别名', trigger: 'change' }],
  categoryId: [{ required: true, message: '请选择文章分类', trigger: 'change' }],
  tags: [{ required: true, message: '请选择或输入文章标签', trigger: 'change' }]
}
const leftToolbar =
  'undo redo clear | bold italic strikethrough quote | ul ol table hr | link code image tip todo-list emoji'
const showDrawer = ref(false)
const isOriginal = ref(true)
const isEncrypt = ref(false)
const src = ref(defaultImg)
</script>

<template>
  <div class="editor">
    <n-card :bordered="false">
      <div class="editor-header">
        <n-input v-model:value="form.title" placeholder="文章标题" />
        <n-button class="middle">暂存</n-button>
        <n-button type="primary" @click="showDrawer = true">发布</n-button>
      </div>
    </n-card>

    <v-md-editor :disabled-menus="[]" :left-toolbar="leftToolbar" class="editor-content" height="calc(100% - 74px)" />

    <n-drawer v-model:show="showDrawer" :width="502" placement="right">
      <n-drawer-content title="文章配置">
        <n-scrollbar>
          <n-form label-width="100px" label-position="left" :model="form" :rules="rules">
            <n-form-item label="文章别名" path="alias">
              <n-input autocomplete="off" v-model.trim="form.alias" placeholder="文章别名" />
            </n-form-item>
            <n-form-item label="文章分类" path="categoryId">
              <n-select v-model:value="form.tags" filterable :options="categoryOptions" placeholder="选择分类" />
            </n-form-item>
            <n-form-item label="文章标签" path="tags">
              <n-select
                v-model:value="form.tags"
                tag
                filterable
                multiple
                :options="categoryOptions"
                placeholder="选择标签"
              />
            </n-form-item>
            <n-form-item label="阅读配置">
              <n-checkbox v-model:checked="form.isOriginal" label="是否原创" />
              <n-checkbox v-model:checked="form.allowComment" label="开启评论" />
              <n-checkbox v-model:checked="form.isTop" label="是否置顶" />
              <n-checkbox v-model:checked="isEncrypt" label="加密" />
            </n-form-item>
            <n-form-item v-if="!form.isOriginal" label="作者">
              <n-input v-model.trim="form.author" placeholder="文章作者" />
            </n-form-item>
            <n-form-item v-if="!form.isOriginal" label="文章出处">
              <n-input v-model.trim="form.source" placeholder="文章地址" />
            </n-form-item>
            <n-form-item v-if="isEncrypt" label="访问密码">
              <n-input type="password" autocomplete="new-password" v-model.trim="form.password" show-password />
            </n-form-item>

            <n-form-item label="封面图" class="thumbnail">
              <n-image :src="src" width="300" />

              <n-input
                class="img-src"
                autocomplete="off"
                v-model.trim="form.thumbnail"
                placeholder="点击封面图选择图片，或直接输入图片地址"
              />
            </n-form-item>

            <n-form-item label="文章摘要">
              <n-input type="textarea" v-model:value="form.summary" placeholder="为空则自动截取文章内容" />
            </n-form-item>

            <n-form-item label="SEO 关键字">
              <n-input type="textarea" v-model:value="form.seoKeywords" placeholder="英文逗号分隔，为空则取文章标签" />
            </n-form-item>

            <n-form-item label="SEO 描述">
              <n-input type="textarea" v-model:value="form.seoDesc" placeholder="为空则取文章摘要" />
            </n-form-item>

            <n-form-item>
              <n-space>
                <n-button>保存</n-button>
                <n-button type="primary">发布</n-button>
              </n-space>
            </n-form-item>
          </n-form>
        </n-scrollbar>
      </n-drawer-content>
    </n-drawer>
  </div>
</template>

<style lang="scss" scoped>
.editor {
  height: 100%;
}
.editor-header {
  display: flex;
  align-items: center;
  .middle {
    margin: 0 20px;
  }
}

.n-image {
  border-radius: 4px;
}

.n-form {
  padding-right: 14px;
}
</style>

<style lang="scss">
.v-md-editor {
  box-shadow: none;
}
.v-md-editor__toolbar {
  padding: 20px;
}
.thumbnail .n-form-item-blank {
  align-items: flex-start;
  flex-direction: column;
  .n-input {
    margin-top: 20px;
  }
}
</style>
