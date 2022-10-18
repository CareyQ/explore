<script setup lang="ts">
import { commitMap } from '@/mock/system'
import CommitMap from './CommitMap.vue'
</script>

<template>
  <aside class="page-side">
    <div class="count">
      <div class="count-item">
        <span class="number">214</span>
        <span class="desc">天</span>
      </div>
      <div class="count-item">
        <span class="number">2</span>
        <span class="desc">片刻</span>
      </div>
      <div class="count-item">
        <span class="number">3</span>
        <span class="desc">文章</span>
      </div>
    </div>

    <CommitMap :commitMap="commitMap" />
  </aside>
</template>

<style lang="scss" scoped>
.page-side {
  max-width: 15rem;
  width: 100%;
  padding-left: 1.25rem;
  > * {
    margin-bottom: 0.75rem;
  }
}

.count {
  display: flex;
  justify-content: space-between;
  background-color: var(--gray-light);
  padding: 10px;
  border-radius: 4px;
}

.count-item {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  border-right: 1px solid var(--gray);
  &:last-child {
    border: none;
  }
}

.number {
  line-height: 1.6;
  font-size: 1.25rem;
  font-weight: bold;
}
.desc {
  opacity: 0.5;
}
</style>
