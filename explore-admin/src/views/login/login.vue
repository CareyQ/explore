<script setup lang="ts">
import { reactive } from 'vue'
import { useUserStore } from '@/stores/modules/user'
import type { LoginInfo } from '@/interface/User'

const userStore = useUserStore()

const loginInfo: LoginInfo = reactive({
  username: '',
  password: ''
})

const handleLogin = async () => {
  await userStore.Login(loginInfo)
}
</script>

<template>
  <div class="login-wapper">
    <div class="login">
      <h1 class="title">Explore</h1>
      <div class="login-form">
        <div class="form-itme">
          <input type="text" placeholder="账号" v-model="loginInfo.username" />
        </div>
        <div class="form-itme">
          <input type="password" placeholder="密码" v-model="loginInfo.password" />
        </div>
        <div class="form-btn">
          <button type="button" class="login-btn" @click="handleLogin">登 录</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.login-wapper {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background-color: #2b3e50;
  color: #fff;
}

.login {
  padding: 56px 40px 56px 40px;
  background-color: #34495d;
  border-radius: 14px;
  box-shadow: 10px 10px 20px rgba(33, 44, 55, 0.3);
  input {
    background: transparent;
    border: 0;
    outline: 0;
    color: #fff;
    font-size: 16px;
    &::-webkit-input-placeholder {
      color: rgba(255, 255, 255, 0.7);
    }
  }
  button {
    background: transparent;
  }
}

.title {
  margin-bottom: 24px;
  font-weight: bold;
  font-size: 34px;
  text-align: center;
}

.form-itme {
  margin-bottom: 20px;
  padding: 10px 0 4px;
  width: 200px;
  border-bottom: 2px solid #fff;
}

.form-btn {
  margin-top: 36px;
  text-align: center;
}

.login-btn {
  cursor: pointer;
  color: #fff;
  border: 1px solid #fff;
  border-radius: 16px;
  width: 100%;
  height: 34px;
  transition: 0.25s ease;
  &:hover {
    background-color: rgba(255, 255, 255, 0.0875);
  }
}
</style>
