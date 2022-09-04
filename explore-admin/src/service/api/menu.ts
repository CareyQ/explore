import { get } from '../request'

/**
 * 获取菜单
 */
export const getMenus = async () => {
  return await get('/menu/admin/list')
}
