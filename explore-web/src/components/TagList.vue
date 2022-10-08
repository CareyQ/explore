<script setup lang="ts">
const props = defineProps<{
  data: TagList[]
  type: number
}>()

const index = ref(props.data[0].id)
</script>

<template>
  <div class="list">
    <div v-if="data.length === 0" class="tag">全部</div>
    <div v-else class="tag-list">
      <div v-for="item in data" :key="item.id" :class="['tag', index === item.id ? 'active' : '']">
        {{ item.name }}
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.list {
  position: relative;
  padding-bottom: 8px;
  margin-bottom: 1.25rem;
  &::after {
    margin: 0 -2.5rem;
    position: absolute;
    bottom: 0;
    content: '';
    display: block;
    height: 1px;
    width: calc(100% + 5rem);
    background-color: var(--gray);
  }
}
.tag-list {
  display: flex;
  align-items: center;
}

.tag {
  position: relative;
  z-index: 1;
  cursor: pointer;
  margin-right: 20px;
  padding: 0 12px;
  line-height: 28px;
  border-bottom: 2px solid transparent;
  border-radius: 6px;
  &:hover {
    background-color: #f0f2f4;
  }
  &::after {
    position: absolute;
    bottom: -10px;
    left: 0;
    content: '';
    display: block;
    height: 2px;
    width: 100%;
    background-color: transparent;
  }
}
.tag.active {
  color: var(--primary);
  &::after {
    background-color: var(--primary);
  }
}
</style>
