export const menusRes: Menu[] = [
  {
    id: 1,
    title: '首页',
    name: 'Home',
    component: 'home/index',
    isBlank: false,
    router: '/',
    icon: 'fluent:home-24-regular',
    group: 1,
    children: []
  },
  {
    id: 2,
    title: '片刻',
    name: 'Moment',
    component: 'moment/index',
    isBlank: false,
    router: '/moment',
    icon: 'ri:wechat-line',
    group: 1,
    children: []
  },
  {
    id: 7,
    title: '文章',
    name: 'Article',
    component: 'article/index',
    isBlank: false,
    router: '/article',
    icon: 'ri:book-3-line',
    group: 1,
    children: []
  },

  {
    id: 8,
    title: '留言',
    name: 'Comment',
    component: 'comment/index',
    isBlank: false,
    router: '/comment',
    icon: 'majesticons:comment-2-text-line',
    group: 2,
    children: []
  },
  {
    id: 8,
    title: '画廊',
    name: 'Gallery',
    component: 'gallery/index',
    isBlank: false,
    router: '/gallery',
    icon: 'ph:google-photos-logo',
    group: 2,
    children: []
  },

  {
    id: 9,
    title: '博客归档',
    name: 'Archive',
    component: 'archive/index',
    isBlank: false,
    router: '/archive',
    icon: '',
    group: 3,
    children: []
  },
  {
    id: 9,
    title: '友情链接',
    name: 'Link',
    component: 'link/index',
    isBlank: false,
    router: '/link',
    icon: '',
    group: 3,
    children: []
  },
  {
    id: 10,
    title: '关于博主',
    name: 'About',
    component: 'about/index',
    isBlank: false,
    router: '/about',
    icon: '',
    group: 3,
    children: []
  },
  {
    id: 10,
    title: '隐私政策',
    name: 'Privacy',
    component: 'privacy/index',
    isBlank: false,
    router: '/privacy',
    icon: '',
    group: 3,
    children: []
  }
]
