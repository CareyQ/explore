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
    show: true,
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
    show: true,
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
    show: true,
    children: []
  },
  {
    id: 7,
    title: '文章',
    name: 'ArticleDetail',
    component: 'article/detail',
    isBlank: false,
    router: '/article/:id',
    icon: '',
    group: 1,
    show: false,
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
    show: true,
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
    show: true,
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
    show: true,
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
    show: true,
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
    show: true,
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
    show: true,
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
    desc: '现在看到的是一篇文章哦，首页为简洁的图文圈子模式哦。现在看到的是一篇文章哦，首页为简洁的图文圈子模式哦。现在看到的是一篇文章哦，首页为简洁的图文圈子模式哦。',
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
  ],
  hotMoment: [
    { title: '我们素不相识，开心是送你的第一份礼物😃', date: '2020-10-20' },
    { title: '音乐时间哈哈😃🎶', date: '2020-10-21' },
    { title: '来啦 看不懂的12w🍌', date: '2020-10-22' },
    { title: '宝安西湾红树林 & 南头古城 📷', date: '2020-10-23' },
    { title: '春节随笔：人总是要长大的呀', date: '2020-10-24' }
  ]
}

export const moment: ArticleItem[] = [
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
      'https://www.rawchen.com/wp-content/uploads/2022/08/P2fb3ApYyBMLVYMs86KB-540x1024.jpg',
      'https://www.rawchen.com/wp-content/uploads/2022/08/joCiMgqjw4YCYjHxgRCD-583x1024.jpg',
      'https://www.rawchen.com/wp-content/uploads/2022/08/uhV2DK5l98pdpkcM6LHF-683x1024.jpg'
    ]
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
  }
]

export const article: ArticleItem[] = [
  {
    id: 2,
    type: 1,
    title: '圈子诞生',
    desc: '现在看到的是一篇文章哦，首页为简洁的图文圈子模式哦。现在看到的是一篇文章哦，首页为简洁的图文圈子模式哦。现在看到的是一篇文章哦，首页为简洁的图文圈子模式哦。',
    date: '2022年8月17日',
    location: '云南省 · 昆明市',
    like: 3,
    comment: 0,
    category: '分类一',
    files: ['https://www.rawchen.com/wp-content/themes/pix/img/banner.jpg']
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
  },
  {
    id: 2,
    type: 1,
    title: '圈子诞生',
    desc: '现在看到的是一篇文章哦，首页为简洁的图文圈子模式哦。现在看到的是一篇文章哦，首页为简洁的图文圈子模式哦。现在看到的是一篇文章哦，首页为简洁的图文圈子模式哦。',
    date: '2022年8月17日',
    location: '云南省 · 昆明市',
    like: 3,
    comment: 0,
    category: '分类一',
    files: ['https://www.rawchen.com/wp-content/themes/pix/img/banner.jpg']
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
  },
  {
    id: 2,
    type: 1,
    title: '圈子诞生',
    desc: '现在看到的是一篇文章哦，首页为简洁的图文圈子模式哦。现在看到的是一篇文章哦，首页为简洁的图文圈子模式哦。现在看到的是一篇文章哦，首页为简洁的图文圈子模式哦。',
    date: '2022年8月17日',
    location: '云南省 · 昆明市',
    like: 3,
    comment: 0,
    category: '分类一',
    files: ['https://www.rawchen.com/wp-content/themes/pix/img/banner.jpg']
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

export const articleDetail = {
  title: 'Adams主题问题收集与更新计划',
  content:
    '<p>已经很久很久没有更新博客了，也实在不知道写点什么，算下来写博客已经有五个年头了，今年是产量最低的一年，真的很想重拾写作乐趣，但是每每打开编辑器，却又不知从何写起，想来已经过了折腾的年纪。</p> <p>毕业了，转正了，工作谈不上顺心，但是也是能应付的过来，但是就是对博客没有从前那种热情了，也不知道这博客还能坚持开多久，真的是有种风雨中摇曳的感觉。</p> <p>有些比较隐私的话想写又不能写，工作的城市也没有能聊这种私密话题的朋友，感觉略郁闷，闷久了就更是不想写点什么了。</p>  <p>昨天一整天在公司几乎什么都没做，逛豆瓣逛知乎，非常羡慕一些人的生活，莫名的感到压力很大，总感觉自己年纪还不大，还可以玩两年，可是转眼已经22岁，在我们那里都是该结婚的年纪了，而我？存款没有、对象没有、工资低、房子没有、车子没有，感觉人生真TMD失败，初中毕业的同学孩子都快上小学了，日了狗了。。。</p> <p>很多大事像买房买车还得靠父母，以我这种特殊情况，早日财务自由才能大胆的去追求喜欢的人和事，为了这，是得好好拼一拼。</p> <h3>关于工作</h3> <p>感觉自己就像个缩头乌龟，自以为待在合肥工作，拿着一般的工资，做着一般的工作，不用早起挤公共交通，不用承担高额的房租、水电费很幸运，其实不过是自己不愿意去承受一线城市的紧张的工作与生活而选择的逃避方式罢了。</p> <p>机会摆在那里，薪资摆在那里，自己不去争取却又偷偷羡慕，也真是窝囊可以，所以暗暗计划从这个月开始攒钱，<span style="text-decoration: underline;"><strong>明年8月</strong></span>去一线城市工作。</p> <p>心仪的城市：成都、珠三角、北京或者长三角，迟早要迈出这一步。</p> <h3>减肥是永恒不变的话题</h3> <p>这话题估计是我17年下半年的全部话题了，7月份体重75kg，已经肥的自己都不想看自己了，于是打开百度地图看了下周边环境，规划线路每天跑步减肥，每天3-5公里的目标，老实说，最开始1公里都跑不下来，小腿跑的也是像石头一样硬，跑了几天之后感觉越跑越轻松，直到能一口气跑3、4公里，坚持了一个来月，瘦了10+斤，但是也因为天天跑步导致膝盖和胯骨有点伤，停了很长时间没有跑，恢复了也懒得跑了，现在体重68-70浮动。</p> <p>微信的头像目标是瘦20斤，要减到64才能换头像，所以还得再坚持坚持，因为搬了新的环境，所以又打开了地图，重新规划跑步线路，计划2、4、6小跑3-5公里，希望早日瘦下来，要是能找个对象就更妙了。</p> <p id="YpngafY"><img width="860" height="380" src="https://biji-cdn.wuzii.com/wp-content/uploads/img_59c1be2e132fd.png" alt="" srcset="https://biji-cdn.wuzii.com/wp-content/uploads/img_59c1be2e132fd.png 860w, https://biji-cdn.wuzii.com/wp-content/uploads/img_59c1be2e132fd-768x339.png 768w" sizes="(max-width: 860px) 100vw, 860px" class="alignnone size-full wp-image-5032 "></p> <p>先规划3公里，慢慢来吧。</p> <h3>学习计划</h3> <p>公司的考勤系统为了方便外勤人员打卡，打算做手机端让他们打卡，脑抽的我提议做微信小程序，结果这档子事儿自然就分给了我，好吧，摸索着做<strong>微信小程序</strong></p> <p>博客的“关于”页面里面自己也是介绍了非专业设计这个技能，但是实在是太不专业了，也就只是停留在用PS画画页面的地步，所以打算定期搞搞再自学一下设计（希望不要懒癌发作）</p> <p>前端，越是呆的久，越觉得水深，整天出新技术，我现在连vue都搞不懂，更别提什么node.js什么的了，所以得研究研究</p> <p>PHP这东西，当初阴差阳错的来了现在的公司做前端，结果现在整天写PHP，基本上是整天学着搞了</p> <h3>其它</h3> <p>本来都开始对搞一辈子基失去了信心，但是看了一些帖子又恢复了点斗志，嗯，挣钱！</p>',
  like: 5,
  view: 200,
  date: '2022-10-12',
  prev: {
    id: 1,
    title: '上一篇文章'
  },
  next: {
    id: 2,
    title: 'next Post'
  },
  tags: [
    { id: 1, name: 'tag1' },
    { id: 1, name: 'tag2' }
  ],
  category: 2,
  categoryName: '分类一'
}

export const archive = {
  categories: [
    { id: 1, name: '分类一', count: 2 },
    { id: 1, name: '分类二', count: 12 },
    { id: 1, name: '分类三', count: 22 },
    { id: 1, name: '分类四', count: 12 }
  ],
  tags: [
    { id: 1, name: 'tag1' },
    { id: 1, name: 'tag2' },
    { id: 1, name: 'tag2' },
    { id: 1, name: 'tag2' },
    { id: 1, name: 'tag2' },
    { id: 1, name: 'tag2' },
    { id: 1, name: 'tag2' },
    { id: 1, name: 'tag2' },
    { id: 1, name: 'tag2' },
    { id: 1, name: 'tag2' }
  ],
  articles: [
    {
      year: '2022',
      data: [
        { id: 1, name: 'WordPress社交营销优化之OpenGraph', date: '10-08' },
        { id: 1, name: 'WordPress社交营销优化之OpenGraph', date: '10-08' }
      ]
    },
    {
      year: '2021',
      data: [
        { id: 1, name: 'WordPress社交营销优化之OpenGraph', date: '10-08' },
        { id: 1, name: 'WordPress社交营销优化之OpenGraph', date: '10-08' },
        { id: 1, name: 'WordPress社交营销优化之OpenGraph', date: '10-08' }
      ]
    },
    {
      year: '2020',
      data: [
        { id: 1, name: 'WordPress社交营销优化之OpenGraph', date: '10-08' },
        { id: 1, name: 'WordPress社交营销优化之OpenGraph', date: '10-08' },
        { id: 1, name: 'WordPress社交营销优化之OpenGraph', date: '10-08' },
        { id: 1, name: 'WordPress社交营销优化之OpenGraph', date: '10-08' }
      ]
    }
  ]
}

export const comment = [
  {
    id: 1,
    username: '毒舌编辑部',
    url: 'baidu.com',
    avatar: 'https://p3-passport.byteimg.com/img/user-avatar/6f3962fda9c64632f66b6fb0bed00b06~100x100.awebp',
    isAdmin: false,
    comment: '你最喜欢《万万没想到》+《报告老板》里的那句台词？[脱单doge]',
    date: '2022-10-22 23:43',
    replies: [
      {
        id: 2,
        username: '巨型二踢脚',
        url: 'baidu.com',
        avatar: 'https://p3-passport.byteimg.com/img/user-avatar/16b3feede6491fd8d3d0307c381f3dc3~100x100.awebp',
        comment: '你这丧门星[脱单doge]',
        date: '2022-10-22 23:43'
      },
      {
        id: 3,
        url: 'baidu.com',
        username: '圈圈圆圆圈圈゜',
        date: '2022-10-22 23:43',
        avatar: 'https://p3-passport.byteimg.com/img/user-avatar/de91406887b41e02523012c7721c9559~100x100.awebp',
        comment: '我先来：你这丧门星[脱单doge]你这丧门星[脱单doge]这是大自然的规律呀！[微笑]你这丧门星[脱单doge]'
      },
      {
        id: 4,
        replyUsername: '圈圈圆圆圈圈゜',
        replyUrl: 'baidu.com',
        username: '回复的',
        url: 'baidu.com',
        date: '2022-10-22 23:43',
        avatar: 'https://p3-passport.byteimg.com/img/user-avatar/5f60c35881d03b607b7793d4e87eb278~100x100.awebp',
        comment: '我先来：这是大自然的规律呀！[微笑]'
      }
    ]
  },
  {
    id: 5,
    username: '毒舌编辑部',
    url: 'baidu.com',
    avatar: 'https://p3-passport.byteimg.com/img/user-avatar/39965b0d97af0026968b7a18e46214a9~100x100.awebp',
    isAdmin: false,
    comment: '你最喜欢《万万没想到》+《报告老板》里的那句台词？[脱单doge]',
    date: '2022-10-22 23:43',
    replies: [
      {
        id: 2,
        username: '巨型二踢脚',
        url: 'baidu.com',
        avatar: 'https://p3-passport.byteimg.com/img/user-avatar/2f91c3b431747d6a62c0e2fa30566ed1~100x100.awebp',
        comment: '你这丧门星[脱单doge]',
        date: '2022-10-22 23:43'
      },
      {
        id: 3,
        url: 'baidu.com',
        username: '圈圈圆圆圈圈゜',
        date: '2022-10-22 23:43',
        avatar: 'https://p3-passport.byteimg.com/img/user-avatar/2f91c3b431747d6a62c0e2fa30566ed1~100x100.awebp',
        comment: '我先来：这是大自然的规律呀！[微笑]'
      },
      {
        id: 4,
        replyUsername: '圈圈圆圆圈圈゜',
        replyUrl: 'baidu.com',
        url: 'baidu.com',
        username: '回复的',
        date: '2022-10-22 23:43',
        avatar: 'https://p3-passport.byteimg.com/img/user-avatar/2f91c3b431747d6a62c0e2fa30566ed1~100x100.awebp',
        comment: '我先来：这是大自然的规律呀！[微笑]'
      }
    ]
  }
]
