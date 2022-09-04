import { get, post } from '../request'

export const saveTag = (tag: any) => {
  return post<Tag>('/tag/save', tag)
}

export const getTags = () => {
  return get<Tag[]>('/tag/list')
}
