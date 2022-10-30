<script setup lang="ts">
import { useRouteStore } from '@/stores/modules/route'
import { useTocStore } from '@/stores/modules/toc'

const router = useRouter()
const routeStore = useRouteStore()
const tocStore = useTocStore()

const menus = computed(() => routeStore.menus)
const isArticle = ref(false)
const isToc = ref(true)

const toc = computed(() => {
  return tocStore.toc
})

watch(
  () => router.currentRoute.value.matched.at(-1)?.path,
  (toPath) => {
    if (toPath !== '/article/:id') {
      isArticle.value = false
      isToc.value = false
    } else {
      isArticle.value = true
    }
  }
)

const calculateRouter = () => {
  if (toc.value) {
    isToc.value = true
  } else {
    isToc.value = false
  }

  if (router.currentRoute.value.matched.at(-1)?.path === '/article/:id') {
    isArticle.value = true
  } else {
    isArticle.value = false
    isToc.value = false
  }
}

calculateRouter()
</script>

<template>
  <aside class="sidebar">
    <div class="sidebar-inner">
      <div class="tabs" v-if="isArticle && toc">
        <div :class="['tab', isToc && 'active']" @click="isToc = true">
          <carbon:catalog />
        </div>
        <div :class="['tab', !isToc && 'active']" @click="isToc = false">
          <line-md:list />
        </div>
      </div>

      <transition name="fade">
        <div class="sidebar-toc card" v-if="isToc">
          <Toc :toc="toc" />
        </div>

        <div v-else class="sidebar-content">
          <div class="card" v-for="(items, index) in menus" :key="index">
            <div class="link" v-for="item in items" :key="item.path">
              <router-link v-if="item && item.meta && item.meta.show" :to="item.path">
                <component :is="item.meta.icon" class="icon" />
                <span>{{ item.meta.title }}</span>
              </router-link>
            </div>
          </div>
        </div>
      </transition>
    </div>
  </aside>
</template>

<style lang="scss" scoped>
@keyframes bounceInUp {
  from,
  75%,
  to {
    animation-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
  }

  from {
    transform: translate3d(0, 10px, 0);
  }

  75% {
    transform: translate3d(0, 5px, 0);
  }

  to {
    transform: translate3d(0, 0, 0);
  }
}

.fade-enter-active {
  animation: bounceInUp 0.25s ease-in;
}

.sidebar {
  position: relative;
  font-size: 1rem;
}

.sidebar-inner {
  position: sticky;
  top: 1rem;
}

.link {
  margin-bottom: 4px;
  a {
    display: flex;
    align-items: center;
    padding: 8px 10px;
    border-radius: 8px;
    &:hover {
      background-color: #eaedf1;
    }
  }
  .icon {
    margin-right: 8px;
    font-size: 1.125rem;
  }
}

a.router-link-active.router-link-exact-active {
  background-color: var(--primary);
  color: var(--white);
}

.tabs {
  position: absolute;
  display: flex;
  flex-direction: column;
  left: -40px;
  top: 0;
}

.tab {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 1rem;
  width: 28px;
  height: 40px;
  cursor: pointer;
  border-radius: 6px;
  transition: all 0.2s ease-in;
}

.tab.active {
  background-color: var(--white);
  box-shadow: var(--shadow);
  color: var(--primary);
}
</style>
