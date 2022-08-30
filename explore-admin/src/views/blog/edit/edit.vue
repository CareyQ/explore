<script setup lang="ts">
import { ElCard, ElInput, ElSelect, ElMessage } from 'element-plus'
import Edit from '@/components/editor/markdownEditor.vue'
import { ref } from 'vue'
import defaultImg from '@/assets/img/placeholder.jpg'

const drawer = ref(false)
const src = ref(defaultImg)
</script>

<template>
  <div class="editor">
    <el-card shadow="never" class="editor-header">
      <div class="editor-header-content">
        <el-input placeholder="文章标题" />
        <el-button>暂存</el-button>
        <el-button type="primary" @click="drawer = true">发布</el-button>
      </div>
    </el-card>

    <Edit class="editor-content" />

    <el-drawer v-model="drawer" title="文章配置" direction="rtl">
      <el-scrollbar height="100%">
        <el-form label-width="100px" label-position="left">
          <el-form-item label="文章别名">
            <el-input autocomplete="off" />
          </el-form-item>
          <el-form-item label="文章分类">
            <el-select placeholder="Please select activity area">
              <el-option label="Area1" value="shanghai" />
              <el-option label="Area2" value="beijing" />
            </el-select>
          </el-form-item>
          <el-form-item label="文章标签">
            <el-input autocomplete="off" />
          </el-form-item>
          <el-form-item label="阅读配置">
            <el-checkbox label="是否原创" name="type" />
            <el-checkbox label="开启评论" name="type" />
            <el-checkbox label="是否置顶" name="type" />
            <el-checkbox label="加密" name="type" />
          </el-form-item>
          <el-form-item label="作者">
            <el-input autocomplete="off" />
          </el-form-item>
          <el-form-item label="文章出处">
            <el-input autocomplete="off" />
          </el-form-item>
          <el-form-item label="访问密码">
            <el-input type="password" autocomplete="new-password" show-password />
          </el-form-item>
          <el-form-item label="封面图">
            <el-image :src="src">
              <template #placeholder>
                <div class="image-slot">Loading<span class="dot">...</span></div>
              </template>
            </el-image>
            <el-input class="img-src" autocomplete="off" placeholder="点击封面图选择图片，或直接输入图片地址" />
          </el-form-item>
          <el-form-item label="文章摘要">
            <el-input type="textarea" placeholder="为空则自动截取文章内容" />
          </el-form-item>
          <el-form-item label="SEO 关键字">
            <el-input type="textarea" placeholder="英文逗号分隔，为空则取文章标签" />
          </el-form-item>
          <el-form-item label="SEO 描述">
            <el-input type="textarea" placeholder="为空则取文章摘要" />
          </el-form-item>
          <el-form-item>
            <el-button>保存</el-button>
            <el-button>发布</el-button>
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
</style>

<style scoped>
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
