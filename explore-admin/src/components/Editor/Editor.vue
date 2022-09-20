<script setup lang="ts">
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
  <v-md-editor :disabled-menus="[]" :left-toolbar="leftToolbar" @change="onChange" class="editor-content" />
</template>

<style lang="scss">
.v-md-editor {
  box-shadow: none;
  border: 1px solid #dcdfe6;
  border-radius: 8px;
}

.v-md-textarea-editor textarea {
  border-radius: 0 0 0 8px;
}
</style>
