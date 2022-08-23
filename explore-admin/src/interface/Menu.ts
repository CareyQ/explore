export interface Menu {
  id: number
  title: string
  name: string
  route: string
  icon: string
  component: string
  children: Menu[]
  isBlank: boolean
}
