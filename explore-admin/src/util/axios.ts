import Axios from 'axios'
import { ElMessage } from 'element-plus'

const axios = Axios.create({
  baseURL: 'http://127.0.0.1:8888/explore/api',
  timeout: 8000
})

axios.interceptors.response.use((res) => {
  console.log(res.data);
  if (res.data.code !== 0) {
    ElMessage.error(res.data.showMsg)
  }

  return res.data
}, (error) => {
  if (error.toString().includes('timeout')) {
    ElMessage.error('网络繁忙，请稍后再试')
  } else {
    ElMessage.error(`请求失败：${error.message}`)
  }
})

export default axios
