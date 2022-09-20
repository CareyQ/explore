import { get, post } from '../request'

export const saveArticle = (params: any) => {
  return post('/article/save', params)
}

export const getArticlePage = (params: any) => {
  return post<Page>('/article/page', params)
}
