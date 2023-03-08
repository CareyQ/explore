<script setup lang="ts">
import type { Res, ResourceCategory, Resource } from '@/model'
import type {
  UploadProps,
  UploadFile,
  UploadRawFile,
  UploadInstance,
  UploadUserFile,
  TabsPaneContext,
  TabPaneName
} from 'element-plus'
import axios from '@/util/axios'
import ResourceDetail from './ResourceDetail.vue'

const selection = ref()
const delTab = ref()
const resourceCategoryRef = ref()
const uploadBtnText = ref('确认上传')
const selectionFile = ref<any[]>([])
const selectionText = ref('当页全选')

const tabsData = ref<ResourceCategory[]>([])
const resources = ref<Resource[]>([])
const uploadFiles = ref<UploadUserFile[]>([])
const uploadRef = ref<UploadInstance>()

const delDialogVisible = ref(false)
const saveDialogVisible = ref(false)
const uploadDialogVisible = ref(false)
const resourceCategoryLoading = ref(false)
const uploadBtnLoading = ref(false)
const delBtnEnable = ref(false)
const showId = ref()
const detailVisible = ref(false)

const resourceCategoryRules = {
  name: [{ required: true, message: '请输入分类名称', trigger: 'change' }]
}

const queryForm = reactive({
  categoryId: '',
  name: '',
  type: '',
  current: 1,
  size: 18,
  total: 0
})

const resourceCategory: ResourceCategory = reactive({
  id: '',
  name: '',
  isEncrypt: false,
  isShow: false,
  categoryDesc: '',
  password: ''
})

const getResourceCategories = async () => {
  const { data } = await axios.get('/resource/category/list')
  tabsData.value = data
  queryForm.categoryId = data[0].id
  getResources()
}

const editResourceCategory = async () => {
  const { data } = await axios.get(`/resource/category/detail?id=${selection.value}`)
  saveDialogVisible.value = true
  nextTick(() => {
    Object.assign(resourceCategory, data)
  })
}

const delResourceCategory = async () => {
  const res = (await axios.delete(`/resource/category/del?id=${delTab.value}`)) as Res
  if (res.code === 0) {
    ElMessage.success(res.showMsg)
    getResourceCategories()
    selection.value = tabsData.value[0].id
  }
  delDialogVisible.value = false
}

const saveResourceCategory = async () => {
  resourceCategoryRef.value?.validate(async (valid: boolean) => {
    if (!valid) {
      return
    }
    resourceCategoryLoading.value = true
    const res = (await axios.post('/resource/category/save', resourceCategory)) as Res

    if (res.code === 0) {
      ElMessage.success(res.showMsg)
      getResourceCategories()
      saveDialogVisible.value = false
    }
    resourceCategoryLoading.value = false
  })
}

const submitUpload = () => {
  const params = new FormData()
  params.append('categoryId', selection.value)
  let size = 0
  uploadFiles.value.forEach((val) => {
    if (val.raw) {
      params.append('files', val.raw)
    }
    if (val.size) {
      size += val.size
    }
  })

  if (size > 10485760) {
    ElMessage.error('一次最多上传 10M 文件')
    uploadFiles.value = []
    return
  }

  uploadBtnText.value = '上传中...'
  uploadBtnLoading.value = true
  axios
    .post('/resource/batch/upload', params, {
      headers: { 'Content-Type': 'multipart/form-data' }
    })
    .then((res: any) => {
      if (res.code === 0) {
        ElMessage.success(res.showMsg)
        getResources()
        uploadDialogVisible.value = false
      }
    })
    .catch((err) => {})
    .finally(() => {
      uploadBtnLoading.value = false
      uploadBtnText.value = '确认上传'
    })
}

const getResources = async () => {
  const { data } = (await axios.post('/resource/page', queryForm)) as Res
  resources.value = data.records
  queryForm.current = data.current
  queryForm.size = data.size
  queryForm.total = data.total
}

const handleTabClick = (pane: TabsPaneContext) => {
  const name = pane.props.name as number
  selection.value = name
}

const handleTabDel = (name: TabPaneName) => {
  delDialogVisible.value = true
  delTab.value = name
}

const saveDialogClose = () => {
  resourceCategoryRef.value?.resetFields()
  resourceCategory.id = ''
}

onMounted(async () => {
  await getResourceCategories()
  nextTick(() => {
    selection.value = tabsData.value[0].id
  })
})

const categoryId = computed(() => {
  return selection.value
})

const checkBoxHandle = (resourceId: any) => {
  const index = selectionFile.value.indexOf(resourceId)

  if (index >= 0) {
    selectionFile.value.splice(index, 1)
  } else {
    selectionFile.value.push(resourceId)
  }
}

const selectAll = () => {
  if (selectionText.value === '当页全选') {
    selectionFile.value = []
    resources.value.forEach((e) => {
      selectionFile.value.push(e.id)
    })
    selectionText.value = '取消选择'
  } else {
    selectionFile.value = []
  }
}

const batchOperate = async (type: number) => {
  const params = {
    ids: selectionFile.value,
    operationType: type
  }
  const res = (await axios.post('/resource/batch/operate', params)) as Res
  if (res.code === 0) {
    ElMessage.success(res.showMsg)
    getResourceCategories()
  }
}

const showDetail = (id: string) => {
  showId.value = id
  detailVisible.value = true
}
</script>

<template>
  <div>
    <el-card>
      <template #header>
        <div class="flex-between">
          <div>
            <el-button type="primary" @click="editResourceCategory">编辑当前资源分类</el-button>
            <el-button type="primary" @click="uploadDialogVisible = true">
              <el-icon class="icon-btn" :size="18" style="margin-right: 1rem">
                <UisUploadAlt />
              </el-icon>
              上 传
            </el-button>
            <el-button type="warning" v-if="selectionFile.length > 0" @click="selectAll">
              {{ selectionText }}
            </el-button>
            <el-button type="danger" v-if="selectionFile.length > 0" :disabled="delBtnEnable" @click="batchOperate(0)">
              删除
            </el-button>
          </div>
          <div>
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
        </div>
      </template>

      <el-tabs
        v-model="selection"
        tab-position="top"
        type="card"
        @tab-click="handleTabClick"
        @tab-remove="handleTabDel"
        @tab-add="saveDialogVisible = true"
        editable
      >
        <div v-if="tabsData.length > 0">
          <el-tab-pane :name="item.id" v-for="(item, index) in tabsData" :key="index" :label="item.name">
            <el-row :gutter="14" v-if="resources && resources.length > 0">
              <el-col v-for="(item, index) in resources" :key="index" :span="4">
                <div class="file-item" @click="showDetail(item.id)">
                  <div class="checkBox">
                    <input
                      type="checkbox"
                      :id="item.id"
                      :checked="selectionFile.indexOf(item.id) >= 0"
                      @click="checkBoxHandle(item.id)"
                    />
                    <label :for="item.id" />
                  </div>

                  <div class="cover flex-center" @click="">
                    <div
                      v-if="item?.type === 'image'"
                      class="img"
                      :style="`background-image: url(${item?.path});`"
                    ></div>
                    <span v-else class="cover-text flex-align-center">
                      {{ item.name.substring(item?.name.lastIndexOf('.') + 1) }}
                    </span>
                  </div>
                  <div class="name" @click="">
                    <el-tooltip :content="item.name">
                      <span>{{ item.name }}</span>
                    </el-tooltip>
                  </div>
                </div>
              </el-col>
            </el-row>

            <el-empty v-else />
          </el-tab-pane>
        </div>

        <el-empty v-else />
      </el-tabs>

      <el-pagination
        small
        background
        layout="prev, pager, next"
        v-model:current-page="queryForm.current"
        v-model:page-size="queryForm.size"
        :total="queryForm.total"
        @current-change="getResources"
      />
    </el-card>

    <el-dialog v-model="delDialogVisible" title="删除资源分类" width="25%">
      <span>是否确定要删除资源分类？</span>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="delDialogVisible = false">取消</el-button>
          <el-button type="danger" @click="delResourceCategory"> 确定 </el-button>
        </span>
      </template>
    </el-dialog>

    <el-dialog v-model="saveDialogVisible" title="保存资源分类" width="25%" @closed="saveDialogClose">
      <el-form ref="resourceCategoryRef" :model="resourceCategory" :rules="resourceCategoryRules" label-position="top">
        <el-form-item label="资源分类名称" prop="name">
          <el-input v-model="resourceCategory.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="访问密码" prop="value">
          <el-input
            type="password"
            v-model="resourceCategory.password"
            autocomplete="off"
            placeholder="不需要加密则无需填写"
            show-password
          />
        </el-form-item>
        <el-form-item label="是否显示" prop="isShow">
          <el-switch v-model="resourceCategory.isShow" />
        </el-form-item>
        <el-form-item label="资源分类描述" prop="categoryDesc">
          <el-input type="textarea" v-model.trim="resourceCategory.categoryDesc" placeholder="用户门户网站展示" />
        </el-form-item>
      </el-form>

      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="saveResourceCategory">保 存</el-button>
        </span>
      </template>
    </el-dialog>

    <el-dialog v-model="uploadDialogVisible" title="上传文件" width="30%" @closed="uploadFiles = []">
      <el-upload ref="uploadRef" v-model:file-list="uploadFiles" :auto-upload="false" drag multiple>
        <el-icon class="icon-btn" :size="48">
          <IcRoundCloudUpload />
        </el-icon>

        <div class="el-upload__text">点击选择文件或拖拽文件到此</div>
      </el-upload>

      <template v-if="uploadFiles.length > 0" #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="submitUpload" :loading="uploadBtnLoading">{{ uploadBtnText }}</el-button>
        </span>
      </template>
    </el-dialog>

    <ResourceDetail :id="showId" :visible="detailVisible" @visible="(value) => (detailVisible = value)" />
  </div>
</template>

<style lang="scss" scoped>
.el-col {
  margin-bottom: 1.6rem;
}

.el-row {
  min-height: 55rem;
}

.file-item {
  position: relative;
  border: 1px solid var(--el-border-color);
  border-radius: 0.4rem;
  overflow: hidden;
  cursor: pointer;

  &:hover {
    .checkBox label {
      display: block;
    }
  }
}

.cover {
  height: 13rem;
  overflow: hidden;
  background-color: #f9f9f9;
}

.img {
  width: 100%;
  height: 100%;
  background-size: cover;
  background-position: center;
}

.name {
  padding: 0.4rem 1rem;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  color: $primary-color;
  opacity: 0.6;
  span {
    font-size: 1.4rem;
  }
}

.checkBox {
  position: absolute;
  right: 1rem;
  top: 0.6rem;

  input {
    display: none;
  }

  label {
    display: none;
    width: 1.5rem;
    height: 1.5rem;
    cursor: pointer;
    background-color: $white-color;
    border: 1px solid $primary-color;
    border-radius: 0.4rem;
    &::before {
      display: inline-block;
      content: ' ';
      width: 0.9rem;
      border: 0.2rem solid $white-color;
      height: 0.5rem;
      border-top: none;
      border-right: none;
      transform: rotate(-45deg);
      top: 0.4rem;
      left: 0.3rem;
      position: absolute;
      opacity: 0;
    }
  }

  input:checked + label {
    background-color: $primary-color;
    &::before {
      opacity: 1;
      transform: all 0.5s;
    }
  }
}

.file-item:has(> .checkBox input:checked) {
  border-color: $primary-color;
  .checkBox label {
    display: block;
  }
}

.cover-text {
  text-transform: capitalize;
  font-size: 4rem;
  color: #cccccc;
  height: 100%;
}
</style>
