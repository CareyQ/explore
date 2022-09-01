<script setup lang="ts">
import { ElCard, ElSelect, ElButton, ElMessage } from 'element-plus'
</script>

<template>
  <el-card shadow="never">
    <template #header>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-input placeholder="请输入标题或内容" clearable />
        </el-form-item>
        <el-form-item>
          <el-select placeholder="请选择分类">
            <el-option label="Zone one" value="shanghai" />
            <el-option label="Zone two" value="beijing" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-select placeholder="请选择状态">
            <el-option label="Zone one" value="shanghai" />
            <el-option label="Zone two" value="beijing" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-select placeholder="是否原创">
            <el-option label="Zone one" value="shanghai" />
            <el-option label="Zone two" value="beijing" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-date-picker
            type="daterange"
            unlink-panels
            range-separator="To"
            start-placeholder="创建开始日期"
            end-placeholder="创建结束日期"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary">查询</el-button>
          <el-button>重置</el-button>
        </el-form-item>
      </el-form>
    </template>
  </el-card>
</template>
