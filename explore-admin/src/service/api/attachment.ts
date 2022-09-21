import { get, post } from '../request'

export const saveAttachmentCategory = (params: any) => {
  return post('/attachment/category/save', params)
}

export const getAttachmentCategoryList = () => {
  return get<AttachmentCategory[]>('/attachment/category/list')
}

export const getAttachmentPage = (params: any) => {
  return post<Page>('/attachment/page', params)
}

export const getAttachmentCategory = (params: any) => {
  return get<AttachmentCategoryDetail>('/attachment/category/detail?id=' + params)
}
