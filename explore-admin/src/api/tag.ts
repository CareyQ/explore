import axios from '../utils/request'
import type { Tag } from '@/interface/Blog'

export const saveTag = (category: any) => {
  return axios.post<Tag>('/tag/save', category)
}

export const getTags = () => {
  return axios.get<Tag[]>('/tag/list')
}
