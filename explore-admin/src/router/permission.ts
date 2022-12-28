import { useRouteStore } from '@/stores/modules/route'
import router from '@/router'

router.beforeEach(async (to, from, next) => {
  if (to.fullPath === '/') {
    next({ name: 'Dashboard' })
  } else {
    next()
  }
})
