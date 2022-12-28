<script setup lang="ts">
import Editor from '@/components/Editor/Editor.vue'
import defaultImg from '@/assets/img/placeholder.jpg'
import { ElMessage } from 'element-plus'
import { getCategories, saveArticle, getTags } from '@/service'

const form = ref({
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

const drawer = ref(false)
const isEncrypt = ref(false)
const src = ref(defaultImg)
const categories = ref<Category[]>([])
const tags = ref<Tag[]>([])
const rules = {
  title: [{ required: true, message: '请输入文章标题', trigger: 'change' }],
  alias: [{ required: true, message: '请输入文章别名', trigger: 'change' }],
  categoryId: [{ required: true, message: '请选择文章分类', trigger: 'change' }],
  tags: [{ required: true, message: '请选择或输入文章标签', trigger: 'change' }]
}

const getData = () => {
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

onMounted(() => {
  getData()
})

const save = () => {
  if (!form.value.title) {
    ElMessage.error('文章标题不能为空')
    return
  }

  if (!form.value.original || !form.value.content) {
    ElMessage.error('文章内容不能为空')
    return
  }
  saveArticle(form.value).then((res) => {
    if (res.code === 0) {
      ElMessage.success(res.showMsg)
    } else {
      ElMessage.error(res.showMsg)
    }
  })
}

const onEditorChange = (content: any) => {
  form.value.original = content.text
  form.value.content = content.html
}
</script>

<template>
  <div class="edit">
    <el-card>
      <template #header>
        <div class="card-header">
          <h6>编辑文章</h6>
        </div>
      </template>

      <hr />

      <div class="header">
        <div class="header-content">
          <el-input v-model.trim="form.title" placeholder="请输入文章标题" />
          <el-button @click="save">暂 存</el-button>
          <el-button type="primary" @click="drawer = true">发 布</el-button>
        </div>
      </div>

      <Editor @editor-change="onEditorChange" />
    </el-card>

    <el-drawer v-model="drawer" title="文章配置" direction="rtl">
      <el-scrollbar height="100%">
        <el-form label-width="90px" label-position="left" :model="form" :rules="rules">
          <el-form-item label="文章别名" prop="alias">
            <el-input autocomplete="off" v-model.trim="form.alias" placeholder="文章别名" />
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
          <el-form-item v-if="!form.isOriginal" label="作者">
            <el-input autocomplete="off" v-model.trim="form.author" />
          </el-form-item>
          <el-form-item v-if="!form.isOriginal" label="文章出处">
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
            <el-button @click="save">保存</el-button>
            <el-button type="primary">发布</el-button>
          </el-form-item>
        </el-form>
      </el-scrollbar>
    </el-drawer>
  </div>
</template>

<style lang="scss" scoped>
.edit {
  height: calc(100% - 100px);
}
.el-card {
  height: 100%;
}
.header {
  margin: 20px 0 30px;
}
.header-content {
  display: flex;
  align-items: center;
  .el-input {
    margin-right: 20px;
  }
}

.el-scrollbar {
  padding-right: 16px;
}

.el-image {
  width: 60%;
  border-radius: 4px;
  margin-bottom: 16px;
}
</style>

<style lang="scss">
.edit {
  .el-card__body {
    height: calc(100% - 60px);
  }
  .el-drawer__header {
    margin-bottom: 16px;
  }
}

.v-md-editor {
  height: calc(100% - 110px);
}
</style>
