export interface CategoryData {
  readOnly: boolean
  loading: boolean
  form: Category
  categories: Category[]
}

export interface Category {
  id: string | number
  name: string
  alias: string
  hits: number
  sort: number
}

export interface Tag {
  id: string | number
  name: string
  alias: string
}

export interface Article {
  id: string | number
  alias: string
  title: string
  categoryId: string | number
  summary: string
  allowComment: boolean
  isTop: boolean
  isOriginal: boolean
  author: string
  source: string
  password: string
  thumbnail: string
  seoKeywords: string
  seoDesc: string
  tags: []
  content: string
  original: string
}
