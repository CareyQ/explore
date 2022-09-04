import { get, post } from '../request'

export const saveCategory = (category: any) => {
  return post('/category/save', category)
}

export const getCategories = () => {
  return get<Category[]>('/category/list')
}
