import Layout from '@/views/layout/index.vue'
import type { RouteRecordRaw } from 'vue-router'
import { Icon } from '@iconify/vue'
import { ElIcon } from 'element-plus'

const routes: RouteRecordRaw[] = [
  {
    name: 'Layout',
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        name: 'Dashboard',
        path: '/dashboard',
        meta: {
          title: '仪表盘',
          icon: iconRender('akar-icons:dashboard')
        },
        component: () => import('@/views/dashboard/index.vue')
      },

      {
        name: 'Blog',
        path: '/blog',
        meta: {
          title: '博客管理',
          icon: iconRender('akar-icons:book-close')
        },
        component: () => import('@/views/blog/index.vue'),
        redirect: '/blog/post',
        children: [
          {
            name: 'Post',
            path: '/blog/post',
            meta: {
              title: '博客列表'
            },
            component: () => import('@/views/blog/post/index.vue')
          },
          {
            name: 'Publish',
            path: '/blog/publish',
            meta: {
              title: '发布文章'
            },
            component: () => import('@/views/blog/publish/index.vue')
          },
          {
            name: 'Meta',
            path: '/blog/meta',
            meta: {
              title: '分类/标签'
            },
            component: () => import('@/views/blog/meta/index.vue')
          },
          {
            name: 'Link',
            path: '/blog/link',
            meta: {
              title: '友链管理'
            },
            component: () => import('@/views/blog/meta/index.vue')
          }
        ]
      },

      {
        name: 'Resource',
        path: '/resource',
        meta: {
          title: '资源管理',
          icon: iconRender('akar-icons:folder')
        },
        component: () => import('@/views/resource/index.vue')
      },

      {
        name: 'Message',
        path: '/message',
        meta: {
          title: '评论留言',
          icon: iconRender('akar-icons:chat-dots')
        },
        component: () => import('@/views/message/index.vue')
      },

      {
        name: 'Author',
        path: '/author',
        meta: {
          title: '作者信息',
          icon: iconRender('akar-icons:person')
        },
        component: () => import('@/views/author/index.vue')
      },

      {
        name: 'System',
        path: '/system',
        meta: {
          title: '系统管理',
          icon: iconRender('akar-icons:settings-horizontal')
        },
        component: () => import('@/views/system/index.vue'),
        redirect: '/system/web/index',
        children: [
          {
            name: 'SystemWeb',
            path: '/system/web/index',
            meta: {
              title: '前台设置'
            },
            component: () => import('@/views/system/web/index.vue')
          },
          {
            name: 'SystemAdmin',
            path: '/system/admin/index',
            meta: {
              title: '后台设置'
            },
            component: () => import('@/views/system/admin/index.vue')
          },
          {
            name: 'Config',
            path: '/system/config/index',
            meta: {
              title: '配置管理'
            },
            component: () => import('@/views/system/config/index.vue')
          }
        ]
      }
    ]
  }
]

export function iconRender(icon: string) {
  return () => h(ElIcon, null, { default: () => h(Icon, { icon }) })
}

export default routes
