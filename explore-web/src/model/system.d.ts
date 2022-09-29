type Menu = {
  id: number
  title: string
  name: string
  router: string
  icon: string
  component: string
  group: number
  children: Menu[]
  isBlank: boolean
}
