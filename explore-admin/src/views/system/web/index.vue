<script setup lang="ts">
import MenuSetting from './components/menu-setting.vue'

const menuSettingRef = ref()
const menuSettingRef2 = ref()
const tabWidth = ref()
const index = ref(1)

const bgStyle = reactive({
  width: '',
  left: ''
})

onMounted(() => {
  bgStyle.width = menuSettingRef.value.offsetWidth + 'px'
  bgStyle.left = menuSettingRef.value.offsetLeft + 'px'
})

const tabClick = (indexOf: number, event: any) => {
  bgStyle.left = event.currentTarget.offsetLeft + 'px'
  index.value = indexOf
}
</script>

<template>
  <div class="web-setting">
    <div class="set-header">
      <div ref="menuSettingRef" class="link" @click="tabClick(1, $event)">菜单设置</div>
      <div class="link" @click="tabClick(2, $event)">其他设置</div>
      <div class="link" @click="tabClick(3, $event)">其他设置</div>
      <div class="link-bg" :style="bgStyle"></div>
    </div>

    <MenuSetting v-show="index === 1" />
  </div>
</template>

<style lang="scss" scoped>
.set-header {
  position: relative;
  display: flex;
  align-items: center;
  margin-bottom: 60px;
  width: 33.333%;
  border-radius: 0.75rem;
  padding: 0.25rem;
  background-color: #f1f1f1;
}

.link {
  flex: 1 1 auto;
  padding: 0.25rem 0;
  border-radius: 0.5rem;
  color: var(--text-dark-color);
  text-align: center;
  z-index: 3;
  cursor: pointer;
}

.link-bg {
  position: absolute;
  height: 80%;
  width: 210px;
  top: 4px;
  flex: 1 1 auto;
  box-shadow: 0 1px 5px 1px #ddd;
  background-color: var(--white);
  border-radius: 0.5rem;
  z-index: 1;
  transition: all 0.5s ease 0s;
}
</style>
