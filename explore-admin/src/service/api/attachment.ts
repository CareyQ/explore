import { get, post } from '../request'

export const saveAttachmentCategory = (params: any) => {
  return post('/attachment/category/save', params)
}

export const getAttachmentCategoryList = () => {
  return get<AttachmentCategory[]>('/attachment/category/list')
}
