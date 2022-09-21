<script setup lang="ts">
import { getAttachmentCategoryList, saveAttachmentCategory, getAttachmentPage, getAttachmentCategory } from '@/service'
import { ElMessage } from 'element-plus'
import type { UploadProps, UploadFile, UploadUserFile, TabsPaneContext } from 'element-plus'

const categories = ref<AttachmentCategory[]>([])
const files = ref<Page>()
const categoryRef = ref()
const categoryForm = reactive({
  id: '' || 0,
  name: '',
  categoryDesc: '',
  password: '',
  isShow: true
})

const categoryDialogVisible = ref(false)
const uploadDialogVisible = ref(false)
const categoryLoading = ref(false)
const rules = {
  name: [{ required: true, message: '请输入分类名称', trigger: 'change' }],
  isShow: [{ required: true, message: '请选择是否显示', trigger: 'change' }]
}
const uploadData = reactive({
  categoryId: '' || 0
})

const filePage = reactive({
  categoryId: uploadData.categoryId,
  type: '',
  current: 1,
  size: 10
})

const uploadFiles = ref<UploadUserFile[]>([])

const getData = () => {
  getAttachmentCategoryList().then((res) => {
    if (res.code === 0 && res.data) {
      categories.value = res.data
      uploadData.categoryId = res.data[0].id
      getFilePage()
    } else {
      ElMessage.error(res.showMsg)
    }
  })
}

onMounted(() => {
  getData()
})

const saveCategory = () => {
  categoryRef.value.validate((valid: boolean) => {
    if (!valid) {
      return
    }
    categoryLoading.value = true
    saveAttachmentCategory(categoryForm).then((res) => {
      if (res.code === 0) {
        ElMessage.success(res.showMsg)
        categoryRef.value.resetFields()
        categoryDialogVisible.value = false
        getData()
      } else {
        ElMessage.error(res.showMsg)
      }
      categoryLoading.value = false
    })
  })
}

const getFilePage = () => {
  filePage.categoryId = uploadData.categoryId
  getAttachmentPage(filePage).then((res) => {
    if (res.code === 0) {
      files.value = res.data
    } else {
      ElMessage.error(res.showMsg)
    }
  })
}

const handleTabClick = (pane: TabsPaneContext) => {
  const name = pane.props.name
  uploadData.categoryId = name
  getFilePage()
}

const handleUpload = (response: any, file: UploadFile) => {
  if (!response || response.code !== 0) {
    ElMessage.error('上传失败，请重试')
    const index = uploadFiles.value.findIndex((e) => e == file)
    uploadFiles.value.splice(index, 1)
  }
}

const handleCloseUpload = () => {
  uploadFiles.value = []
  getFilePage()
}

const handleCloseCategory = () => {
  categoryRef.value.resetFields()
  getAttachmentCategoryList().then((res) => {
    if (res.code === 0 && res.data) {
      categories.value = res.data
    } else {
      ElMessage.error(res.showMsg)
    }
  })
}

const checkIds = ref<number[]>([])
const handleCheck = (id: number) => {
  const index = checkIds.value.indexOf(id)
  if (index >= 0) {
    checkIds.value.splice(index, 1)
  } else {
    checkIds.value.push(id)
  }
}

const handleEditCategory = (id: number) => {
  getAttachmentCategory(id).then((res) => {
    if (res.code === 0) {
      const data = res.data
      if (data) {
        categoryForm.id = data.id
        categoryForm.name = data.name
        categoryForm.categoryDesc = data.categoryDesc
        categoryForm.password = data.password
        categoryForm.isShow = data.isShow
        categoryDialogVisible.value = true
      }
    } else {
      ElMessage.error(res.showMsg)
    }
  })
}
</script>

<template>
  <div class="file">
    <el-card>
      <template #header>
        <div class="filter">
          <div class="handle">
            <el-button type="primary" @click="categoryDialogVisible = true">编辑当前分类</el-button>
            <el-button type="primary" @click="uploadDialogVisible = true">
              <el-icon class="icon-btn" :size="18">
                <UisUploadAlt />
              </el-icon>
              上 传
            </el-button>
            <el-button type="primary" v-if="checkIds.length > 0">
              <el-icon class="icon-btn" :size="18">
                <IcBaselineChecklistRtl />
              </el-icon>
              全 选
            </el-button>
            <el-button v-if="checkIds.length > 0" type="danger" plain>
              <el-icon class="icon-btn" :size="18">
                <MdiDelete />
              </el-icon>
              删 除
            </el-button>
          </div>

          <el-form :inline="true">
            <el-form-item prop="tagId">
              <el-input placeholder="文件名称" />
            </el-form-item>
            <el-form-item prop="categoryId">
              <el-select placeholder="文件类型" clearable> </el-select>
            </el-form-item>

            <el-form-item>
              <el-button type="primary">查 询</el-button>
            </el-form-item>
          </el-form>
        </div>
      </template>
      <el-tabs
        v-model="uploadData.categoryId"
        tab-position="top"
        type="card"
        editable
        @tab-add="categoryDialogVisible = true"
        @tab-click="handleTabClick"
      >
        <el-tab-pane :name="item.id" v-for="(item, index) in categories" :key="index" :label="item.name">
          <el-row :gutter="14" v-if="files?.records && files?.records.length > 0">
            <el-col v-for="(item, index) in files?.records" :key="index" :span="4">
              <div class="file-item">
                <el-checkbox @change="handleCheck(item?.id)" />
                <div class="cover" :style="`background-image: url(item?.path);`">
                  <img :src="item?.path" :alt="item?.name" v-if="item?.type === 'image'" />
                  <span v-else class="cover-text">{{ item?.name.substring(item?.name.lastIndexOf('.') + 1) }}</span>
                </div>
                <div class="name">
                  <span>{{ item?.name }}</span>
                </div>
              </div>
            </el-col>
          </el-row>

          <el-empty v-else description="description" />
        </el-tab-pane>
      </el-tabs>
    </el-card>

    <el-dialog v-model="categoryDialogVisible" title="保存文件分类" width="30%" @closed="handleCloseCategory">
      <el-form ref="categoryRef" :model="categoryForm" label-position="top" :rules="rules">
        <el-form-item label="分类名称" prop="name">
          <el-input v-model.trim="categoryForm.name" placeholder="请输入文件分类名称" />
        </el-form-item>
        <el-form-item label="访问密码" prop="password">
          <el-input v-model.trim="categoryForm.password" placeholder="不需要加密则无需填写" />
        </el-form-item>
        <el-form-item label="是否显示" prop="isShow">
          <el-switch v-model="categoryForm.isShow" style="--el-switch-on-color: var(--green)" />
        </el-form-item>
        <el-form-item label="分类描述" prop="categoryDesc">
          <el-input
            type="textarea"
            v-model.trim="categoryForm.categoryDesc"
            placeholder="文件分类描述，用户门户网站展示"
          />
        </el-form-item>
      </el-form>

      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" :loading="categoryLoading" @click="saveCategory">保 存</el-button>
        </span>
      </template>
    </el-dialog>

    <el-dialog v-model="uploadDialogVisible" title="上传文件" width="30%" class="upload" @closed="handleCloseUpload">
      <el-upload
        v-model:file-list="uploadFiles"
        :data="uploadData"
        list-type="picture"
        drag
        action="http://127.0.0.1:8888/explore/api/attachment/upload"
        multiple
        :on-success="handleUpload"
      >
        <el-icon class="icon-btn" :size="48">
          <IcRoundCloudUpload />
        </el-icon>

        <div class="el-upload__text">点击选择文件或拖拽文件到此</div>
      </el-upload>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.filter {
  display: flex;
  justify-content: space-between;
}

.el-card {
  margin-bottom: 30px;
}

.save-category {
  .el-form-item {
    margin-bottom: 0 !important;
  }
}

.el-icon {
  margin-right: 6px;
}

.el-tab-pane {
  display: flex;
  flex-wrap: wrap;
}

.file-item {
  position: relative;
  border: 1px solid var(--el-border-color);
  border-radius: 4px;
  overflow: hidden;
  margin-bottom: 16px;
  cursor: pointer;
  .cover {
    height: 130px;
    overflow: hidden;
  }
  img {
    max-width: 100%;
  }
  .name {
    padding: 4px 10px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    color: var(--text-dark-color);
    opacity: 0.6;
    span {
      font-size: 14px;
    }
  }
  .el-checkbox {
    right: 10px;
    top: 6px;
    position: absolute;
    display: none;
  }
  &:hover {
    .el-checkbox {
      display: block;
    }
  }
  .cover-text {
    display: flex;
    align-items: center;
    justify-content: center;
    text-transform: capitalize;
    font-size: 40px;
    color: #cccccc;
    height: 100%;
  }
}

.file-item:has(> .is-checked) {
  border-color: var(--text-dark-color);
  .el-checkbox {
    display: block;
  }
}

.el-empty {
  margin: 0 auto;
}

.tab-label {
  position: relative;
  .icon-btn {
    position: absolute;
    top: 12px;
    right: -30px;
  }
}
</style>

<style lang="scss">
.upload .el-upload-dragger {
  padding: 15px 10px;
}

.file .el-card__body {
  height: 100%;
}

.el-tabs__item {
  .icon-btn {
    display: none;
  }
  &:hover {
    .icon-btn {
      display: block;
    }
  }
}

.file .el-tabs--top .el-tabs__item.is-top:last-child {
  padding-right: 20px;
}

.filter .el-form--inline .el-form-item {
  margin-bottom: 0 !important;
}
</style>
