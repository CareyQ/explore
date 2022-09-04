import axios from 'axios'

axios.defaults.timeout = 4000
axios.defaults.baseURL = 'http://127.0.0.1:8888/explore/api'

axios.interceptors.request.use(
  function (config) {
    return config
  },
  function (error) {
    return Promise.reject(error)
  }
)

axios.interceptors.response.use(
  function (res) {
    return res.data
  },
  function (error) {
    if (error.toString().includes('timeout')) {
      window.$message.error('网络繁忙，请稍后再试')
    } else {
      window.$message.error(`请求失败：${error.message}`)
    }
  }
)

export const get = <T>(url: string, params?: object): Promise<Result<T>> => {
  return axios.get(url, { params })
}

export const post = <T>(url: string, params?: object): Promise<Result<T>> => {
  return axios.post(url, params)
}

export const put = <T>(url: string, params?: object): Promise<Result<T>> => {
  return axios.put(url, params)
}
