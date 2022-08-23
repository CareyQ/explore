import axios from '../utils/request'

namespace Login {
  export interface LoginDTO {
    username: string
    password: string
  }
}

export const login = (params: Login.LoginDTO) => {
  return axios.post('/user/login', params)
}
