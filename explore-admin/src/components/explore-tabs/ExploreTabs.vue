<script setup lang="ts">
import type { TabItem } from '@/model'

const contentRef = ref()
const headerRef = ref()
const active = ref()
const items = ref<TabItem[]>([])

const emit = defineEmits(['tabClick'])

provide('active', active)

const tabBgStyle = reactive({
  width: '',
  left: ''
})

const getPane = () => {
  const dom = [...contentRef.value.children]

  const arr = []
  for (var i = 0; i < dom.length; i++) {
    arr.push({ title: dom[i].dataset.title })
  }

  active.value = arr[0].title
  items.value = arr
}

onMounted(() => {
  getPane()
  nextTick(() => {
    const firstNode = headerRef.value.children[0]
    tabBgStyle.width = firstNode.offsetWidth + 'px'
    tabBgStyle.left = firstNode.offsetLeft + 'px'
  })
})

const tabClick = (title: string, event: any) => {
  emit('tabClick', title)
  active.value = title
  tabBgStyle.left = event.currentTarget.offsetLeft + 'px'
  tabBgStyle.width = event.currentTarget.offsetWidth + 'px'
}
</script>

<template>
  <div class="explore-tabs">
    <div class="explore-tabs__header flex" ref="headerRef">
      <div class="explore-tab" v-for="(item, index) in items" :key="index" @click="tabClick(item.title, $event)">
        {{ item.title }}
      </div>
      <div class="explore-tab-bg" :style="tabBgStyle"></div>
    </div>

    <div class="explore-tabs__content" ref="contentRef">
      <slot />
    </div>
  </div>
</template>

<style lang="scss" scoped>
.explore-tabs__header {
  position: relative;
  margin-bottom: 6rem;
  padding: 0.6rem;
  width: fit-content;
  color: $primary-color;
  border-radius: 1.2rem;
  background-color: $tabs-bg;
}

.explore-tab {
  flex: 1 1 auto;
  z-index: 3;
  padding: 0.6rem 2.5rem;
  line-height: 1;
  cursor: pointer;
}

.explore-tab-bg {
  position: absolute;
  top: 0.4rem;

  height: 80%;
  box-shadow: 0 1px 5px 1px #ddd;
  background-color: $white-color;
  border-radius: 0.8rem;
  transition: all 0.5s ease 0s;
}
</style>
