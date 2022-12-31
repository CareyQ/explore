<template>
  <router-view v-slot="{ Component }">
    <transition mode="out-in" name="el-fade-in-linear">
      <component :is="Component" />
    </transition>
  </router-view>
</template>
