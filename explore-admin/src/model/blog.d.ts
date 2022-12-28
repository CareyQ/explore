/** 分类配置 */
type Category = {
  id: string | number
  name: string
  alias: string
  count: number | unknown
  hits: number | unknown
  sort: number
}

/** 标签配置 */
type Tag = {
  id: string | number
  name: string
  alias: string
}

/** 文章配置 */
type Article = {
  id: string | number
  alias: string
  title: string
  categoryId: number
  summary: string
  allowComment: boolean
  isTop: boolean
  isOriginal: boolean
  author: string
  source: string
  password: string
  thumbnail: string
  seoKeywords: string
  seoDesc: string
  tags: []
  content: string
  original: string
}

type ArticlePage = {
  id: number
  alias: string
  title: string
  status: number
  categoryName: string
  isTop: boolean
  thumbnail: string
  tagsName: []
  createTime: string
  hits: number
  likes: number
}

type ResourceCategory = {
  id: number
  name: string
  isEncrypt: boolean
  isShow: boolean
}

type ResourceCategoryDetail = {
  id: number
  name: string
  categoryDesc: string
  password: string
  isShow: boolean
}

type Resource = {
  id: number
  name: string
  type: string
  path: string
  size: string | number
  height: string | number
  weight: string | number
  createTime: string
}
