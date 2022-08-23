import axios from '../utils/request'
import type { LoginUser } from '@/interface/User'

namespace Login {
  export interface LoginDTO {
    username: string
    password: string
  }
}

export const login = (params: Login.LoginDTO) => {
  return axios.post<LoginUser>('/user/login', params)
}
