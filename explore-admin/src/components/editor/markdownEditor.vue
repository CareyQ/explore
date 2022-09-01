<script setup lang="ts">
import { ref } from 'vue'

const content = ref('')
const leftToolbar =
  'undo redo clear | bold italic strikethrough quote | ul ol table hr | link code image tip todo-list emoji'

const emit = defineEmits(['editorChange'])

const onChange = (text: string, html: string) => {
  const content = {
    text: text,
    html: html
  }
  emit('editorChange', content)
}
</script>

<template>
  <div class="edit-main">
    <v-md-editor
      v-model="content"
      height="100%"
      :disabled-menus="[]"
      :left-toolbar="leftToolbar"
      @change="onChange"
    ></v-md-editor>
  </div>
</template>

<style lang="scss" scoped>
.edit-main {
  display: flex;
  width: 100%;
  height: 100%;
}

.el-textarea,
.markdown-html {
  width: 50%;
  height: 100%;
  font-size: 16px;
}

.markdown-html {
  background-color: #fbfbfb;
  border: 1px solid var(--el-border-color);
  border-left: 0;
  border-radius: 4px;
}
</style>
<style lang="scss">
.v-md-editor {
  border-radius: 16px;
  box-shadow: none;
}
.v-md-editor__toolbar {
  padding: 20px;
}
.v-md-editor__editor-wrapper {
  border-radius: 0 0 0 16px;
}
</style>
