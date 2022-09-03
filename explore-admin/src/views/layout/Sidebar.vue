<script setup lang="ts">
import { useRouteStore } from '@/stores/modules/route'
import type { MenuProps, MenuOption } from 'naive-ui'
import router from '@/router'

type MenuTheme = NonNullable<MenuProps['themeOverrides']>

const menuTheme: MenuTheme = {
  itemTextColor: '#fff',
  itemIconColor: '#fff',
  arrowColor: '#fff',
  itemTextColorActive: '#fff',
  itemIconColorActive: '#fff',
  itemIconColorActiveHover: '#01adb5',
  itemColorActive: '#01adb5'
}
const routeStore = useRouteStore()

const menus = computed(() => routeStore.menus as GlobalMenuOption[])

const handleUpdateMenu = (key: string, item: MenuOption) => {
  const menuItem = item as GlobalMenuOption
  router.push(menuItem.routePath)
}
</script>

<template>
  <aside class="sidebar">
    <div class="userinfo">
      <img
        class="avatar"
        src="https://lh3.googleusercontent.com/a-/AFdZucpjbUVr3flArvqxYRkyJPX7Zm5HXSq1veda0H6LYA=s96-c-rg-br100"
        alt=""
      />

      <div class="info">
        <h2>CareyQ</h2>
        <p>没放弃，留在这一天...</p>
      </div>
    </div>
    <n-menu :options="menus" @update:value="handleUpdateMenu" :theme-overrides="menuTheme" />
  </aside>
</template>

<style lang="scss" scoped>
.userinfo {
  display: flex;
  padding: 30px 20px;
  align-items: center;
}

.avatar {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  border: 1px solid #fff;
}

.info {
  margin-left: 14px;
  line-height: 1;
  h2 {
    margin-bottom: 10px;
    font-size: 20px;
    font-weight: bold;
  }
  p {
    margin: 0;
    font-size: 12px;
    opacity: 0.8;
  }
}
</style>
