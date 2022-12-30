import { get, post, del } from '../request'

export const getConfigPage = (current: number, size: number) => {
  return get<Page>('/config/page?current=' + current + '&size=' + size)
}

export const saveConfig = (config: any) => {
  return post('/config/save', config)
}

export const delConfig = (id: number) => {
  return del('/config/del?id=' + id)
}
