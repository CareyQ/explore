import { Icon } from '@iconify/vue'
import { ElIcon } from 'element-plus'

export const iconRender = (icon: string) => {
  return () => h(ElIcon, null, { default: () => h(Icon, { icon }) })
}

export const toArray = (data: number) => {
  const arr: number[] = []
  arr.push(data)
  return arr
}
