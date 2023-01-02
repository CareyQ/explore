export interface TabItem {
  title: string
}

export interface Res {
  code: number,
  msg: string,
  showMsg: string,
  data: any
}

export interface Config {
  id: string
  code: string
  value: string
  configDesc: string
}

export interface ResourceCategory {
  id: string
  name: string
  isEncrypt: boolean
  isShow: boolean
  categoryDesc: string
  password: string
}

export interface Resource {
  id: string
  name: string
  type: string
  path: string
  size: string | number
  height: string | number
  weight: string | number
  createTime: string
}
