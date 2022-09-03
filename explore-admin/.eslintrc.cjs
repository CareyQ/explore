/* eslint-env node */
require('@rushstack/eslint-patch/modern-module-resolution')

module.exports = {
  root: true,
  extends: [
    'plugin:vue/vue3-essential',
    'eslint:recommended',
    '@vue/eslint-config-typescript/recommended',
    '@vue/eslint-config-prettier',
    './.eslintrc-auto-import.json'
  ],
  parserOptions: {
    ecmaVersion: 'latest'
  },
  rules: {
    quotes: [2, 'single'],
    semi: [2, 'never'],
    'vue/multi-word-component-names': 'off',
    '@typescript-eslint/no-namespace': 'off',
    '@typescript-eslint/no-explicit-any': ['off'],
    'vue/comment-directive': 'off',
    '@typescript-eslint/ban-ts-comment': 'off',
    'no-undef': 'off'
  }
}
