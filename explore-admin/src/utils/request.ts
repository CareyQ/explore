import axios from 'axios'
import { useMessage } from 'naive-ui'
import { RequestEnums } from '@/model/request'

import type { AxiosInstance, AxiosRequestConfig, AxiosError, AxiosResponse } from 'axios'
import type { Result } from '@/model/request'

const message = useMessage()
const URL = 'http://127.0.0.1:8888/explore/api'
const config = {
  baseURL: URL as string,
  timeout: RequestEnums.TIMEOUT
}

class RequestHttp {
  service: AxiosInstance

  public constructor(config: AxiosRequestConfig) {
    this.service = axios.create(config)

    /**
     * 请求拦截器
     */
    this.service.interceptors.request.use(
      async (config: AxiosRequestConfig) => {
        return {
          ...config
        }
      },
      (err: AxiosError) => {
        Promise.reject(err)
      }
    )

    /**
     * 响应拦截器
     */
    this.service.interceptors.response.use(
      (res: AxiosResponse) => {
        return res.data
      },
      (err: AxiosError) => {
        if (err.toString().includes('timeout')) {
          message.error('网络繁忙，请稍后再试')
        } else {
          message.error(`请求失败：${err.message}`)
        }
      }
    )
  }

  get<T>(url: string, params?: object): Promise<Result<T>> {
    return this.service.get(url, { params })
  }

  post<T>(url: string, params?: object): Promise<Result<T>> {
    return this.service.post(url, params)
  }

  put<T>(url: string, params?: object): Promise<Result<T>> {
    return this.service.put(url, params)
  }
}

export default new RequestHttp(config)
