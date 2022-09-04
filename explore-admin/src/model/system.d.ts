/** 菜单配置 */
type GlobalMenuOption = import('naive-ui').MenuOption & {
  key: string
  label: string
  routeName: string
  routePath: string
  icon?: () => import('vue').VNodeChild
  children?: GlobalMenuOption[]
}

type Result<T> = {
  code: number
  msg: string
  showMsg: string
  data?: T
}
