<script setup lang="ts">
import Draggable from 'vuedraggable'
import type { Category } from '@/interface/Category'
import { ref, watch } from 'vue'

const props = defineProps<{
  list: Category[]
}>()

const categories = ref<Category[]>([])

const update = (value: Category[]) => {
  categories.value = value
}

watch(() => props.list, update)

const emit = defineEmits(['exchangeId', 'edit'])

const onUpdate = ({ oldIndex, newIndex }) => {
  const exchangeId = [props.list[oldIndex].id, props.list[newIndex].id]
  emit('exchangeId', exchangeId)
}

const onEdit = (category: Category) => {
  emit('edit', category)
}
</script>

<template>
  <el-row class="header">
    <el-col :span="2"></el-col>
    <el-col :span="4"> 名称 </el-col>
    <el-col :span="3">文章数</el-col>
    <el-col :span="3">点击数</el-col>
    <el-col :span="3">排序</el-col>
    <el-col :span="5">路径别名</el-col>
    <el-col :span="4"> 操作 </el-col>
  </el-row>

  <draggable v-model="categories" item-key="id" handle=".icon-move" animation="200" @update="onUpdate">
    <template #item="{ element }">
      <el-row>
        <el-col :span="2">
          <i class="icon-move"></i>
        </el-col>
        <el-col :span="4">
          <span class="name">{{ element.name }}</span>
        </el-col>
        <el-col :span="3">0</el-col>
        <el-col :span="3">{{ element.hits }}</el-col>
        <el-col :span="3">
          <el-tag class="ml-2" type="warning">{{ element.sort }}</el-tag>
        </el-col>
        <el-col :span="5">{{ element.alias }}</el-col>
        <el-col :span="4" class="action">
          <el-link type="primary" :underline="false" @click="onEdit(element)">编辑</el-link>
          <el-link type="danger" :underline="false">删除</el-link>
        </el-col>
      </el-row>
    </template>
  </draggable>
</template>

<style lang="scss" scoped>
.icon-move {
  margin-right: 10px;
  cursor: move;
}

.icon-move {
  opacity: 0.6;
  font-size: 16px;
}

.alias {
  font-size: 14px;
  opacity: 0.8;
}

.el-row {
  display: flex;
  align-items: center;
  padding: 18px 0;
  width: 100%;
  border-bottom: 1px solid var(--border-color);
  font-size: 14px;
}

.header {
  padding-top: 0;
  .el-col {
    font-weight: bold;
  }
}

.action > *:first-child {
  margin-right: 16px;
}
</style>
