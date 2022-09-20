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

type Page = {
  pages: number
  records: []
  current: number
  size: number
  total: 0
}
