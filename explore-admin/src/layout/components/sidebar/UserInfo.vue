<template>
  <div class="flex-center user-info">
    <img
      class="avatar"
      src="https://lh3.googleusercontent.com/a-/AFdZucpjbUVr3flArvqxYRkyJPX7Zm5HXSq1veda0H6LYA=s96-c-rg-br100"
      alt="asdf"
    />
    <div class="info">
      <h2 class="username">CareyQ</h2>
      <p class="desc">没放弃，留在这一天...</p>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.user-info {
  padding: 3.2rem 0 2.6rem;
}

.avatar {
  width: 4.8rem;
  height: 4.8rem;
  border-radius: 50%;
  border: 1px dashed $dark-color;
}

.info {
  margin-left: 1.5rem;
  line-height: 1;
}

.username {
  margin-bottom: 1rem;
  font-size: 2rem;
  font-weight: bold;
}

.desc {
  font-size: 1.2rem;
  opacity: 0.8;
}
</style>
