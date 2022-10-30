<script setup lang="ts">
import { getTags, getCategories, getArticlePage } from '@/service'
import { ElMessage } from 'element-plus'

const filterForm = reactive({
  title: '',
  categoryId: '',
  tagId: '',
  status: '',
  isOriginal: '',
  current: 1,
  size: 10
})

const categories = ref<Category[]>([])
const tags = ref<Tag[]>([])
const articlePage = ref<Page>()

const getData = () => {
  queryPage()
  getTags().then((res) => {
    if (res.code === 0 && res.data) {
      tags.value = res.data
    } else {
      ElMessage.error(res.showMsg)
    }
  })
  getCategories().then((res) => {
    if (res.code === 0 && res.data) {
      categories.value = res.data
    } else {
      ElMessage.error(res.showMsg)
    }
  })
}

const actionCell = (row: cellClassName) => {
  if (row.columnIndex === 9) {
    return 'table-action'
  }
}

onMounted(() => {
  getData()
})

const filterRef = ref()
const queryPage = () => {
  getArticlePage(filterForm).then((res) => {
    if (res.code === 0 && res.data) {
      articlePage.value = res.data
    } else {
      ElMessage.error(res.showMsg)
    }
  })
}
</script>

<template>
  <el-card class="articles">
    <div class="filter">
      <el-form ref="filterRef" :model="filterForm" :inline="true" class="demo-form-inline">
        <el-form-item prop="title">
          <el-input v-model.trim="filterForm.title" placeholder="请输入标题" clearable />
        </el-form-item>
        <el-form-item prop="categoryId">
          <el-select v-model="filterForm.categoryId" placeholder="请选择分类" clearable>
            <el-option :label="item.name" :value="item.id" v-for="(item, index) in categories" :key="index" />
          </el-select>
        </el-form-item>
        <el-form-item prop="tagId">
          <el-select v-model="filterForm.tagId" placeholder="请选择标签" clearable>
            <el-option :label="item.name" :value="item.id" v-for="(item, index) in tags" :key="index" />
          </el-select>
        </el-form-item>
        <el-form-item prop="status">
          <el-select v-model="filterForm.status" placeholder="请选择状态" clearable>
            <el-option label="草稿" value="0" />
            <el-option label="已发布" value="1" />
          </el-select>
        </el-form-item>
        <el-form-item prop="isOriginal">
          <el-select v-model="filterForm.isOriginal" placeholder="是否原创">
            <el-option label="原创" value="0" />
            <el-option label="转载" value="1" />
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="queryPage">查询</el-button>
          <el-button @click="filterRef.resetFields()">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div class="data">
      <el-table :data="articlePage?.records" style="width: 100%" :cell-class-name="actionCell">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="title" label="标题" />
        <el-table-column label="状态" width="100">
          <template #default="scope">
            <div class="status">
              <span :class="['dot', scope.row.status === 0 ? 'draft-dot' : 'publish-dot']"></span>
              {{ scope.row.statusName }}
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="categoryName" label="分类" width="120" />
        <el-table-column label="标签">
          <template #default="scope">
            <el-space wrap>
              <el-tag v-for="name in scope.row.tagsName" :key="name">
                {{ name }}
              </el-tag>
            </el-space>
          </template>
        </el-table-column>
        <el-table-column prop="hits" label="浏览量" width="80" />
        <el-table-column prop="likes" label="点赞数" width="80" />
        <el-table-column label="是否置顶" width="100">
          <template #default="scope">
            <el-switch v-model="scope.row.isTop" style="--el-switch-on-color: var(--green)" />
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="160" />

        <el-table-column label="操作" class="table-action" width="100">
          <template #default="scope">
            <el-tooltip effect="dark" content="编辑文章" placement="top">
              <el-icon class="icon-btn" :size="18" color="#8392ab" @click="">
                <FluentDocumentEdit24Filled class="icon" />
              </el-icon>
            </el-tooltip>

            <el-tooltip effect="dark" content="删除文章" placement="top">
              <el-icon class="icon-btn" :size="20" color="#8392ab">
                <MdiDelete class="icon" />
              </el-icon>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </el-card>
</template>

<style lang="scss" scoped>
.el-form-item:last-child {
  margin-top: 0;
}

.status {
  display: flex;
  align-items: center;
}

.dot {
  position: relative;
  margin-right: 8px;
  display: inline-block;
  width: 6px;
  height: 6px;
  border-radius: 50%;
  top: 1px;
}

.draft-dot {
  background-color: var(--yellow);
}

.publish-dot {
  background-color: var(--green);
}
</style>
