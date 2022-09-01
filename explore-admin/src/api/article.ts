import axios from '../utils/request'
import type { Article } from '@/interface/Blog'

export const save = (params: Article) => {
  return axios.post('/article/save', params)
}
