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
  show: boolean
}

type TagList = {
  id: number
  name: string
}

type ArticleItem = {
  id: number
  type: number
  title: string
  desc: string
  date: string
  location: string
  like: number
  comment: number
  category: string
  files: string[]
}

type Commit = {
  date: string
  comment: number
  moment: number
  article: number
}
