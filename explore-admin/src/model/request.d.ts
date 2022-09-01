export interface Result<T> {
  code: number
  msg: string
  showMsg: string
  data?: T
}

export enum RequestEnums {
  TIMEOUT = 20000,
  SUCCESS = 0,
  NOT_LOGIN = 4003
}
