import router from '@/router'
import axios, { AxiosInstance, AxiosRequestConfig, AxiosError, AxiosResponse } from 'axios'
import { useUserStore } from '@/stores/modules/user'
import { ElMessage } from 'element-plus'

interface Result {
  code: number
  msg: string
  showMsg: string
}

interface ResultData<T = unknown> extends Result {
  data?: T
}

enum RequestEnums {
  TIMEOUT = 20000,
  SUCCESS = 0,
  UN_LOGIN = 4003
}

const URL = 'http://127.0.0.1:8888/explore/api'
const config = {
  baseURL: URL as string,
  timeout: RequestEnums.TIMEOUT as number
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
        const sotre = useUserStore()
        const token = sotre.token
        return {
          ...config,
          headers: {
            Authorization: token
          }
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
        if (res.data.code === RequestEnums.UN_LOGIN) {
          ElMessage.error(res.data.showMsg)
          router.replace({ name: 'Login', replace: true })
          return
        }

        return res.data
      },
      (err: AxiosError) => {
        if (err.toString().includes('timeout')) {
          ElMessage.error('网络繁忙，请稍后再试')
        } else {
          ElMessage.error(`请求失败：${err.message}`)
        }
      }
    )
  }

  get<T>(url: string, params?: object): Promise<ResultData<T>> {
    return this.service.get(url, { params })
  }

  post<T>(url: string, params?: object): Promise<ResultData<T>> {
    return this.service.post(url, params)
  }

  put<T>(url: string, params?: object): Promise<ResultData<T>> {
    return this.service.put(url, params)
  }
}

export default new RequestHttp(config)
