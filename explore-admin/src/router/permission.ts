import { useRouteStore } from '@/stores/modules/route'
import router from '@/router'

router.beforeEach(async (to, from, next) => {
  const routerStore = useRouteStore()
  if (routerStore.routes.length <= 0) {
    await routerStore.initRoute()
    next({ path: to.path })
  } else {
    next()
  }
})
