type Result<T> = {
  code: number
  msg: string
  showMsg: string
  data?: T
}

type cellClassName = {
  row: any
  rowIndex: number
  column: TableColumnCtx<any>
  columnIndex: number
}

type Page<T = any> = {
  pages: number
  records: T[]
  current: number
  size: number
  total: 0
}

/** 配置 */
type Config = {
  id: string | number
  code: string | number
  value: string
  configDesc: string
}
