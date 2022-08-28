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
