<script setup lang="ts">
const menuRef = ref()
const menuForm = ref()
const rules = [{}]
</script>

<template>
  <el-row :gutter="20">
    <el-col :span="6">
      <el-card>
        <template #header>
          <h5>保存菜单</h5>
        </template>

        <el-form ref="menuRef" :model="menuForm" :rules="rules" label-position="top">
          <el-form-item label="菜单名称" prop="name">
            <el-input placeholder="菜单名称" />
          </el-form-item>

          <el-form-item label="分类别名" prop="alias">
            <el-input placeholder="页面路径标识，推荐英文" />
          </el-form-item>

          <el-form-item label="分类排序">
            <el-input-number :min="0" controls-position="right" />
          </el-form-item>

          <el-form-item> </el-form-item>
        </el-form>
      </el-card>
    </el-col>

    <el-col :span="18">
      <el-card>
        <template #header>
          <h5>菜单列表</h5>
        </template>
      </el-card>
    </el-col>
  </el-row>
</template>

<style lang="scss" scoped></style>
