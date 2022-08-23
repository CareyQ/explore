import axios from '../utils/request'
import type { Menu } from '@/interface/Menu'

export const getMenu = () => {
  return axios.get<Menu[]>('/menu/admin/list')
}
