import axios from '../utils/request'

/**
 * 获取菜单
 */
export const getMenus = async () => {
  return await axios.get('/menu/admin/list')
}
