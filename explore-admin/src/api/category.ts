import axios from '../utils/request'
import type { Category } from '@/interface/Blog'

export const saveCategory = (category: any) => {
  return axios.post<Category>('/category/save', category)
}

export const getCategories = () => {
  return axios.get<Category[]>('/category/list')
}

export const exchange = (ids: number[]) => {
  return axios.put('/category/exchange', ids)
}
