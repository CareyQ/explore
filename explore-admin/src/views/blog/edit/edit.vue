<script setup lang="ts">
import { ElCard, ElInput, ElMessage } from 'element-plus'
import Edit from '@/components/editor/markdownEditor.vue'
</script>

<template>
  <div class="editor">
    <el-card shadow="never" class="editor-header">
      <el-row :gutter="20">
        <el-col :span="21"><el-input placeholder="文章标题" /></el-col>
        <el-col :span="1.5"><el-button>暂存</el-button></el-col>
        <el-col :span="1.5"><el-button type="primary">发布</el-button></el-col>
      </el-row>
    </el-card>

    <Edit class="editor-content" />
  </div>
</template>

<style lang="scss" scoped>
.editor {
  height: 100%;
}

.editor-header {
  padding: 20px;
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
</style>
