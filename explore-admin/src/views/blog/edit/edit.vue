<script setup lang="ts">
import { ElCard, ElInput, ElSelect, ElMessage } from 'element-plus'
import { getCategories } from '@/api/category'
import { getTags } from '@/api/tag'
import { save } from '@/api/article'
import Edit from '@/components/editor/markdownEditor.vue'
import { reactive, ref } from 'vue'
import defaultImg from '@/assets/img/placeholder.jpg'
import type { Category, Tag } from '@/interface/Blog'
import type { Article } from '@/interface/Blog'

const drawer = ref(false)
const isOriginal = ref(true)
const isEncrypt = ref(false)
const src = ref(defaultImg)
const categories = ref<Category[]>([])
const tags = ref<Tag[]>([])

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

const getCategoryAndTag = () => {
  getCategories().then((res) => {
    if (res.code === 0 && res.data) {
      categories.value = res.data
    } else {
      ElMessage.error(res.showMsg)
    }
  })
  getTags().then((res) => {
    if (res.code === 0 && res.data) {
      tags.value = res.data
    } else {
      ElMessage.error(res.showMsg)
    }
  })
}

getCategoryAndTag()

const rules = {
  title: [{ required: true, message: '请输入文章标题', trigger: 'change' }],
  alias: [{ required: true, message: '请输入文章别名', trigger: 'change' }],
  categoryId: [{ required: true, message: '请选择文章分类', trigger: 'change' }],
  tags: [{ required: true, message: '请选择或输入文章标签', trigger: 'change' }]
}

const saveArticle = () => {
  if (!form.title) {
    ElMessage.error('文章标题不能为空')
    return
  }
  if (!form.original || !form.content) {
    ElMessage.error('文章内容不能为空')
    return
  }
  save(form).then((res) => {
    if (res.code === 0) {
      ElMessage.success(res.showMsg)
    } else {
      ElMessage.error(res.showMsg)
    }
  })
}

const onEditorChange = (content: any) => {
  form.original = content.text
  form.content = content.html
}
</script>

<template>
  <div class="editor">
    <el-card shadow="never" class="editor-header">
      <div class="editor-header-content">
        <el-input v-model.trim="form.title" placeholder="文章标题" prop="title" />
        <el-button @click="saveArticle">暂存</el-button>
        <el-button type="primary" @click="drawer = true">发布</el-button>
      </div>
    </el-card>

    <Edit class="editor-content" @editor-change="onEditorChange" />

    <el-drawer v-model="drawer" title="文章配置" direction="rtl">
      <el-scrollbar height="100%">
        <el-form label-width="100px" label-position="left" :model="form" :rules="rules">
          <el-form-item label="文章别名" prop="alias">
            <el-input autocomplete="off" v-model.trim="form.alias" />
          </el-form-item>
          <el-form-item label="文章分类" prop="categoryId">
            <el-select placeholder="选择分类" v-model="form.categoryId">
              <el-option :label="item.name" :value="item.id" v-for="(item, index) in categories" :key="index" />
            </el-select>
          </el-form-item>
          <el-form-item label="文章标签" prop="tags">
            <el-select
              v-model="form.tags"
              multiple
              filterable
              allow-create
              default-first-option
              placeholder="选择标签"
              style="width: 100%"
            >
              <el-option v-for="item in tags" :key="item.id" :label="item.name" :value="item.id" />
            </el-select>
          </el-form-item>
          <el-form-item label="阅读配置">
            <el-checkbox v-model="form.isOriginal" label="是否原创" />
            <el-checkbox v-model="form.allowComment" label="开启评论" />
            <el-checkbox v-model="form.isTop" label="是否置顶" />
            <el-checkbox v-model="isEncrypt" label="加密" />
          </el-form-item>
          <el-form-item v-if="!isOriginal" label="作者">
            <el-input autocomplete="off" v-model.trim="form.author" />
          </el-form-item>
          <el-form-item v-if="!isOriginal" label="文章出处">
            <el-input autocomplete="off" v-model.trim="form.source" />
          </el-form-item>
          <el-form-item v-if="isEncrypt" label="访问密码">
            <el-input type="password" autocomplete="new-password" v-model.trim="form.password" show-password />
          </el-form-item>
          <el-form-item label="封面图">
            <el-image :src="src">
              <template #placeholder>
                <div class="image-slot">Loading<span class="dot">...</span></div>
              </template>
            </el-image>
            <el-input
              class="img-src"
              autocomplete="off"
              v-model.trim="form.thumbnail"
              placeholder="点击封面图选择图片，或直接输入图片地址"
            />
          </el-form-item>
          <el-form-item label="文章摘要">
            <el-input type="textarea" v-model.trim="form.summary" placeholder="为空则自动截取文章内容" />
          </el-form-item>
          <el-form-item label="SEO 关键字">
            <el-input type="textarea" v-model.trim="form.seoKeywords" placeholder="英文逗号分隔，为空则取文章标签" />
          </el-form-item>
          <el-form-item label="SEO 描述">
            <el-input type="textarea" v-model.trim="form.seoDesc" placeholder="为空则取文章摘要" />
          </el-form-item>
          <el-form-item>
            <el-button @click="saveArticle">保存</el-button>
            <el-button type="primary">发布</el-button>
          </el-form-item>
        </el-form>
      </el-scrollbar>
    </el-drawer>
  </div>
</template>

<style lang="scss" scoped>
.editor {
  height: 100%;
}

.editor-header {
  padding: 20px;
}

.editor-header-content {
  display: flex;
  align-items: center;
  .el-input {
    margin-right: 20px;
  }
}

.editor-content {
  height: calc(100% - 100px);
  .el-card__body {
    height: 100%;
  }
}

.editor-header {
  .el-input {
    font-size: 16px;
  }
}

.el-card {
  margin-bottom: 30px;
}
</style>

<style lang="scss">
.editor .el-card__body {
  padding: 0;
}

.editor-content {
  .el-card__body {
    height: 100%;
  }
}

.el-image {
  width: 60%;
  border-radius: 4px;
}

.img-src {
  margin-top: 20px;
}

.el-scrollbar {
  padding-right: 16px;
}

.demo-image__placeholder .el-image {
  padding: 0 5px;
  max-width: 300px;
  max-height: 200px;
}

.demo-image__placeholder.image-slot {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  background: var(--el-fill-color-light);
  color: var(--el-text-color-secondary);
  font-size: 14px;
}
.demo-image__placeholder .dot {
  animation: dot 2s infinite steps(3, start);
  overflow: hidden;
}
</style>
