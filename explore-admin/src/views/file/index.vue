<script setup lang="ts">
import {
  getAttachmentCategoryList,
  saveAttachmentCategory,
  getAttachmentPage,
  getAttachmentCategory,
  getAttachment
} from '@/service'
import { ElMessage } from 'element-plus'
import type { UploadProps, UploadFile, UploadUserFile, TabsPaneContext } from 'element-plus'
import { fileSize } from '@/utils'

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
const fileDialogVisible = ref(false)
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

const checkTitle = ref('全 选')
const checkFlag = ref(false)
const checkAll = () => {
  if (checkFlag.value === false) {
    // 全选
    files.value?.records.forEach((e) => {
      checkIds.value.push(e?.id)
    })
    checkTitle.value = '取消全选'
    checkFlag.value = true
  } else {
    checkIds.value = []
    checkTitle.value = '全 选'
    checkFlag.value = false
  }
}

const file = reactive<Attachment>({
  id: '',
  name: '',
  type: '',
  path: '',
  size: '',
  height: '',
  weight: '',
  createTime: ''
})

const getFile = (id: number) => {
  getAttachment(id).then((res) => {
    if (res.code === 0) {
      const data = res.data
      if (data) {
        file.id = data.id
        file.name = data.name
        file.type = data.type
        file.path = data.path
        file.size = data.size
        file.height = data.height
        file.weight = data.weight
        file.createTime = data.createTime
        fileDialogVisible.value = true
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
            <el-button type="primary" @click="handleEditCategory(uploadData.categoryId)">编辑当前分类</el-button>
            <el-button type="primary" @click="uploadDialogVisible = true">
              <el-icon class="icon-btn" :size="18">
                <UisUploadAlt />
              </el-icon>
              上 传
            </el-button>
            <el-button type="primary" v-if="checkIds.length > 0" @click="checkAll">
              <el-icon class="icon-btn" :size="18">
                <IcBaselineChecklistRtl />
              </el-icon>
              {{ checkTitle }}
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
                <input
                  type="checkbox"
                  :id="item?.id"
                  :checked="checkIds.indexOf(item?.id) >= 0"
                  @click="handleCheck(item?.id)"
                  class="checkBox"
                />
                <div class="cover" :style="`background-image: url(item?.path);`" @click="getFile(item?.id)">
                  <img :src="item?.path" :alt="item?.name" v-if="item?.type === 'image'" />
                  <span v-else class="cover-text">{{ item?.name.substring(item?.name.lastIndexOf('.') + 1) }}</span>
                </div>
                <div class="name" @click="getFile(item?.id)">
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

    <el-dialog v-model="fileDialogVisible" title="文件详情" :width="file.type === 'image' ? '50%' : '30%'">
      <div class="file-detail">
        <div class="preview" v-if="file.type === 'image'">
          <img :src="file.path" :alt="file.name" />
        </div>

        <el-descriptions direction="vertical" column="1">
          <el-descriptions-item>
            <template #label>
              <div class="desc-name">
                文件名：
                <el-icon class="icon-btn" :size="18" color="#8392ab">
                  <ant-design:edit-outlined />
                </el-icon>
              </div>
            </template>
            {{ file.name }}
          </el-descriptions-item>

          <el-descriptions-item label="文件大小：">
            {{ fileSize(file.size) }}
          </el-descriptions-item>
          <el-descriptions-item label="Remarks">
            <el-tag size="small">School</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="Address"
            >No.1188, Wuzhong Avenue, Wuzhong District, Suzhou, Jiangsu Province</el-descriptions-item
          >
        </el-descriptions>
      </div>
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
  .checkBox {
    right: 10px;
    top: 6px;
    position: absolute;
    display: none;
  }
  &:hover {
    .checkBox {
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

.file-item:has(> .checkBox:checked) {
  border-color: var(--text-dark-color);
  .checkBox {
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

.desc-name {
  display: flex;
  align-items: center;
}

.file-detail {
  display: flex;
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

.file .el-tabs__item.is-active {
  font-weight: bold;
}

.filter .el-form--inline .el-form-item {
  margin-bottom: 0 !important;
}

.file-detail .el-dialog__body {
  padding-top: 10px;
}

.file-detail {
  .el-descriptions__cell {
    padding-bottom: 8px !important;
  }
  .el-descriptions__cell.el-descriptions__label {
    padding-top: 16px;
  }
  .el-descriptions__cell.el-descriptions__content {
    margin-bottom: 20px;
    border-bottom: 1px solid var(--el-border-color);
  }
  .el-descriptions__table tr:first-child .el-descriptions__cell.el-descriptions__label {
    padding-top: 0;
  }
}
</style>
