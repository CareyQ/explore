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
  categoryId: string | number
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
