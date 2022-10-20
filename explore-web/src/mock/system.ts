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

export const monmentTags: TagList[] = [
  {
    id: 1,
    name: '全部'
  },
  {
    id: 2,
    name: '一言'
  },
  {
    id: 7,
    name: '生活'
  }
]

export const articleList: ArticleItem[] = [
  {
    id: 1,
    type: 2,
    title: '',
    desc: '好久没跑啦😶',
    date: '5小时前',
    location: '云南省 · 曲靖市',
    like: 5,
    comment: 6,
    category: '',
    files: ['https://www.rawchen.com/wp-content/uploads/2022/08/pxjmHBajTHFCw2yNZdaf-768x1024.jpg']
  },
  {
    id: 2,
    type: 1,
    title: '圈子诞生',
    desc: '现在看到的是一篇文章哦，首页为简洁的图文圈子模式哦。',
    date: '2022年8月17日',
    location: '云南省 · 昆明市',
    like: 3,
    comment: 0,
    category: '分类一',
    files: ['https://www.rawchen.com/wp-content/themes/pix/img/banner.jpg']
  },
  {
    id: 1,
    type: 2,
    title: '',
    desc: '来啦\n看不懂的12w🍌\n上/这周末干饭生活\n家里来了新主子:皮蛋(金渐层)',
    date: '昨天',
    location: '云南省 · 昆明市',
    like: 5,
    comment: 16,
    category: '',
    files: [
      'https://www.rawchen.com/wp-content/uploads/2022/08/P2fb3ApYyBMLVYMs86KB-540x1024.jpg',
      'https://www.rawchen.com/wp-content/uploads/2022/08/joCiMgqjw4YCYjHxgRCD-583x1024.jpg',
      'https://www.rawchen.com/wp-content/uploads/2022/08/uhV2DK5l98pdpkcM6LHF-683x1024.jpg'
    ]
  },
  {
    id: 2,
    type: 1,
    title: '17CE正在征集测速节点',
    desc: '今天用17CE试试服务器和本地的网速，偶然间看到17CE正在征集测速节点，不过主要针对海外，国内节点暂未...',
    date: '2022年8月13日',
    location: '云南省 · 昆明市',
    like: 2,
    comment: 0,
    category: '分类二',
    files: []
  }
]

export const sidebar: any = {
  commitMap: [
    { article: 0, comment: 1, date: '2022-08-10', moment: 2 },
    { article: 0, comment: 0, date: '2022-08-11', moment: 0 },
    { article: 1, comment: 4, date: '2022-08-12', moment: 3 },
    { article: 4, comment: 1, date: '2022-08-13', moment: 0 },
    { article: 4, comment: 0, date: '2022-08-14', moment: 1 },
    { article: 3, comment: 2, date: '2022-08-15', moment: 2 },
    { article: 4, comment: 4, date: '2022-08-16', moment: 0 },
    { article: 2, comment: 0, date: '2022-08-17', moment: 0 },
    { article: 0, comment: 1, date: '2022-08-18', moment: 4 },
    { article: 0, comment: 0, date: '2022-08-19', moment: 1 },
    { article: 2, comment: 2, date: '2022-08-20', moment: 3 },
    { article: 0, comment: 1, date: '2022-08-21', moment: 4 },
    { article: 4, comment: 4, date: '2022-08-22', moment: 3 },
    { article: 2, comment: 3, date: '2022-08-23', moment: 3 },
    { article: 3, comment: 0, date: '2022-08-24', moment: 1 },
    { article: 0, comment: 1, date: '2022-08-25', moment: 1 },
    { article: 3, comment: 0, date: '2022-08-26', moment: 1 },
    { article: 2, comment: 2, date: '2022-08-27', moment: 0 },
    { article: 3, comment: 0, date: '2022-08-28', moment: 3 },
    { article: 1, comment: 4, date: '2022-08-29', moment: 3 },
    { article: 3, comment: 1, date: '2022-08-30', moment: 1 },
    { article: 4, comment: 1, date: '2022-08-31', moment: 4 },
    { article: 2, comment: 0, date: '2022-09-01', moment: 4 },
    { article: 3, comment: 2, date: '2022-09-02', moment: 1 },
    { article: 1, comment: 3, date: '2022-09-03', moment: 4 },
    { article: 0, comment: 0, date: '2022-09-04', moment: 2 },
    { article: 0, comment: 2, date: '2022-09-05', moment: 1 },
    { article: 0, comment: 2, date: '2022-09-06', moment: 0 },
    { article: 0, comment: 0, date: '2022-09-07', moment: 0 },
    { article: 0, comment: 0, date: '2022-09-08', moment: 0 },
    { article: 3, comment: 1, date: '2022-09-09', moment: 0 },
    { article: 0, comment: 2, date: '2022-09-10', moment: 0 },
    { article: 4, comment: 2, date: '2022-09-11', moment: 4 },
    { article: 4, comment: 0, date: '2022-09-12', moment: 4 },
    { article: 2, comment: 3, date: '2022-09-13', moment: 3 },
    { article: 0, comment: 3, date: '2022-09-14', moment: 0 },
    { article: 3, comment: 3, date: '2022-09-15', moment: 4 },
    { article: 4, comment: 3, date: '2022-09-16', moment: 3 },
    { article: 2, comment: 2, date: '2022-09-17', moment: 4 },
    { article: 2, comment: 0, date: '2022-09-18', moment: 0 },
    { article: 3, comment: 4, date: '2022-09-19', moment: 3 },
    { article: 0, comment: 1, date: '2022-09-20', moment: 4 },
    { article: 3, comment: 3, date: '2022-09-21', moment: 2 },
    { article: 0, comment: 2, date: '2022-09-22', moment: 1 },
    { article: 0, comment: 0, date: '2022-09-23', moment: 0 },
    { article: 3, comment: 0, date: '2022-09-24', moment: 0 },
    { article: 4, comment: 0, date: '2022-09-25', moment: 0 },
    { article: 3, comment: 0, date: '2022-09-26', moment: 0 },
    { article: 3, comment: 4, date: '2022-09-27', moment: 0 },
    { article: 3, comment: 1, date: '2022-09-28', moment: 0 },
    { article: 4, comment: 3, date: '2022-09-29', moment: 0 },
    { article: 1, comment: 2, date: '2022-09-30', moment: 2 },
    { article: 2, comment: 1, date: '2022-10-01', moment: 1 },
    { article: 1, comment: 0, date: '2022-10-02', moment: 4 },
    { article: 4, comment: 0, date: '2022-10-03', moment: 4 },
    { article: 2, comment: 4, date: '2022-10-04', moment: 1 },
    { article: 3, comment: 2, date: '2022-10-05', moment: 4 },
    { article: 0, comment: 3, date: '2022-10-06', moment: 3 },
    { article: 0, comment: 0, date: '2022-10-07', moment: 4 },
    { article: 0, comment: 4, date: '2022-10-08', moment: 1 }
  ],
  hotArticles: [
    { title: '第1篇文章', date: '2020-10-20' },
    { title: '电视剧为啥每集45分钟', date: '2020-10-21' },
    { title: '如何打造专属自媒体品牌', date: '2020-10-22' },
    { title: '至少这条鱼很在乎', date: '2020-10-23' },
    { title: '春节随笔：人总是要长大的呀', date: '2020-10-24' }
  ]
}
