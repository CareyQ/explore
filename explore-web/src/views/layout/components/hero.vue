<template>
  <div class="hero">
    <div class="hero-btns">
      <div class="icon-btn">
        <ri:search-line />
      </div>

      <div class="icon-btn">
        <ri:contrast-2-line />
      </div>
    </div>

    <div class="hero-user">
      <div class="user-text">
        <div class="name">CareyQ</div>
        <div class="desc">为你隐身守候</div>
      </div>
      <img
        src="https://lh3.googleusercontent.com/a-/ACNPEu_Mms0QDPmGvQCn3ZGD-DtJlGQJ2-QYgeQC74B6Yw=s96-c-rg-br100"
        alt=""
        class="avatar"
      />
    </div>
  </div>
</template>

<style lang="scss" scoped>
.hero {
  position: relative;
  padding: 1.25rem 2.5rem;
  color: var(--white);
  height: 14.25rem;
  background-size: cover;
  background-position: center center;
  background-image: url(https://www.rawchen.com/wp-content/themes/pix/img/banner.jpg);
  &::after {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.3);
  }
}

.hero-btns {
  display: flex;
  > * {
    margin-right: 1rem;
  }
}

.hero-user,
.hero-btns {
  position: relative;
  z-index: 1;
}

.hero-user {
  position: absolute;
  bottom: 1.25rem;
  right: 2.5rem;
  display: flex;
  align-items: center;
  .name {
    margin-bottom: 4px;
    font-weight: bold;
  }
  .desc {
    font-size: 0.875rem;
    color: #d6d6d6;
  }
  .avatar {
    margin-left: 1rem;
    width: 58px;
    height: 58px;
    border-radius: 8px;
  }
}
</style>
