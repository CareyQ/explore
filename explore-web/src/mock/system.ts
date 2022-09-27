const menus = [
  {
    id: 1,
    title: '首页',
    name: 'Dashboard',
    component: 'dashboard/index',
    isBlank: false,
    router: '/dashboard',
    icon: 'fluent:home-24-regular',
    children: null
  },
  {
    id: 2,
    title: '博客管理',
    name: 'Blog',
    component: 'blog/index',
    isBlank: false,
    router: '/blog',
    icon: 'akar-icons:book-close',
    children: [
      {
        id: 3,
        title: '文章列表',
        name: 'Article',
        component: 'blog/article/index',
        isBlank: false,
        router: '/blog/article',
        icon: null,
        children: null
      },
      {
        id: 4,
        title: '发布文章',
        name: 'PublishArticle',
        component: 'blog/edit/index',
        isBlank: false,
        router: '/blog/edit',
        icon: null,
        children: null
      },
      {
        id: 5,
        title: '分类/标签',
        name: 'CategoryTag',
        component: 'blog/category-tag/index',
        isBlank: false,
        router: '/blog/category-tag',
        icon: null,
        children: null
      }
    ]
  },
  {
    id: 7,
    title: '文件管理',
    name: 'File',
    component: 'file/index',
    isBlank: false,
    router: '/file',
    icon: 'akar-icons:folder',
    children: null
  },
  {
    id: 8,
    title: '评论留言',
    name: 'Message',
    component: 'message/index',
    isBlank: false,
    router: '/message',
    icon: 'akar-icons:chat-dots',
    children: null
  },
  {
    id: 9,
    title: '作者信息',
    name: 'Author',
    component: 'author/index',
    isBlank: false,
    router: '/author',
    icon: 'akar-icons:person',
    children: null
  },
  {
    id: 10,
    title: '系统设置',
    name: 'System',
    component: 'system/index',
    isBlank: false,
    router: '/system',
    icon: 'akar-icons:settings-horizontal',
    children: [
      {
        id: 11,
        title: '前台设置',
        name: 'SystemWeb',
        component: 'system/web/index',
        isBlank: false,
        router: '/system/web',
        icon: null,
        children: null
      },
      {
        id: 12,
        title: '后台设置',
        name: 'SystemAdmin',
        component: 'system/admin/index',
        isBlank: false,
        router: '/system/admin',
        icon: null,
        children: null
      }
    ]
  }
]
