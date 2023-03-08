<script setup lang="ts">
import type { Res, Config } from '@/model'
import axios from '@/util/axios'

const selectionCode = ref<number[]>([2, 3])

const config = reactive({
  localDomain: {} as Config,
  localPath: {} as Config
})

const getResourceLocalConfig = async (configCode: number[]) => {
  const { data } = await axios.post('/config/list', configCode)
  const configMap = new Map()

  data.forEach((item: Config) => {
    configMap.set(item.code, item)
  })

  config.localDomain = configMap.get(2) as Config
  config.localPath = configMap.get(3) as Config
}

const tabClick = (title: string) => {
  if (title === '本地资源存储') {
    selectionCode.value = [2, 3]
  } else if (title === '邮箱设置') {
  } else {
  }
  getResourceLocalConfig(selectionCode.value)
}

const saveConfig = async (configs: Config[]) => {
  const res = (await axios.post('/config/batch/save', configs)) as Res
  if (res.code === 0) {
    ElMessage.success(res.showMsg)
    getResourceLocalConfig(selectionCode.value)
  }
}

onMounted(() => {
  getResourceLocalConfig(selectionCode.value)
})

const handleLocalPath = (localPathConfig: Config) => {
  let path = localPathConfig.value.replaceAll('\\', '\/')
  if (!path.endsWith('/')) {
    path = path + '/'
  }
  localPathConfig.value = path
  return localPathConfig
}
</script>

<template>
  <div>
    <ExploreTabs @tab-click="tabClick">
      <ExploreTabPane title="本地资源存储">
        <el-card>
          <el-alert title="Nginx 需要配置对应路径" type="info" :closable="false" />
          <el-form style="max-width: 460px">
            <el-form-item label="文件服务域名">
              <el-input v-model="config.localDomain.value" />
            </el-form-item>
            <el-form-item label="资源存储路径">
              <el-input v-model="config.localPath.value" />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="saveConfig([config.localDomain, handleLocalPath(config.localPath)])"
                >保存</el-button
              >
            </el-form-item>
          </el-form>
        </el-card>
      </ExploreTabPane>

      <ExploreTabPane title="邮箱设置">邮箱设置</ExploreTabPane>
      <ExploreTabPane title="缓存管理">缓存管理</ExploreTabPane>
    </ExploreTabs>
  </div>
</template>

<style lang="scss" scoped>
.el-alert {
  margin-bottom: 3rem;
}
</style>
