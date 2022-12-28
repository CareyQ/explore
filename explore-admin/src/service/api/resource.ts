import { get, post } from '../request'

export const saveResourceCategory = (params: any) => {
  return post('/resource/category/save', params)
}

export const getResourceCategeoryList = () => {
  return get<ResourceCategory[]>('/resource/category/list')
}

export const getResourcePage = (params: any) => {
  return post<Page>('/resource/page', params)
}

export const getResourceCategoryDetail = (params: any) => {
  return get<ResourceCategoryDetail>('/resource/category/detail?id=' + params)
}

export const getResourceDetail = (params: any) => {
  return get<Resource>('/resource/detail?id=' + params)
}
