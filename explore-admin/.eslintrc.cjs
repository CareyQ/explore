module.exports = {
  extends: ['plugin:vue/vue3-essential', 'standard-with-typescript', 'plugin:prettier/recommended'],
  parserOptions: {
    ecmaVersion: 'latest',
    sourceType: 'module'
  },
  plugins: ['vue'],
  rules: {
    quotes: [2, 'single'],
    semi: [2, 'never'],
    'no-undef': 'off'
  }
}
