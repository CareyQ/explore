import { createRouter, createWebHistory } from 'vue-router'
import { iconRender } from '@/util'
import Layout from '@/layout/ExploreLayout.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
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
          component: () => import('@/views/dashboard/DashboardIndex.vue')
        },

        {
          name: 'Blog',
          path: '/blog',
          meta: {
            title: '博客管理',
            icon: iconRender('akar-icons:book-close')
          },
          component: () => import('@/views/blog/BlogIndex.vue'),
          redirect: '/blog/post',
          children: [
            {
              name: 'Post',
              path: '/blog/post',
              meta: {
                title: '博客列表'
              },
              component: () => import('@/views/blog/post/BlogPost.vue')
            },
            {
              name: 'Publish',
              path: '/blog/publish',
              meta: {
                title: '发布文章'
              },
              component: () => import('@/views/blog/publish/BlogPublish.vue')
            },
            {
              name: 'Meta',
              path: '/blog/meta',
              meta: {
                title: '分类/标签'
              },
              component: () => import('@/views/blog/meta/BlogMeta.vue')
            },
            {
              name: 'Link',
              path: '/blog/link',
              meta: {
                title: '友链管理'
              },
              component: () => import('@/views/blog/link/BlogLink.vue')
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
          component: () => import('@/views/resource/ResourceIndex.vue')
        },

        {
          name: 'Message',
          path: '/message',
          meta: {
            title: '评论留言',
            icon: iconRender('akar-icons:chat-dots')
          },
          component: () => import('@/views/message/MessageIndex.vue')
        },

        {
          name: 'Author',
          path: '/author',
          meta: {
            title: '作者信息',
            icon: iconRender('akar-icons:person')
          },
          component: () => import('@/views/author/AuthorIndex.vue')
        },

        {
          name: 'System',
          path: '/system',
          meta: {
            title: '系统管理',
            icon: iconRender('akar-icons:settings-horizontal')
          },
          component: () => import('@/views/system/SystemIndex.vue'),
          redirect: '/system/web',
          children: [
            {
              name: 'SystemWeb',
              path: '/system/web',
              meta: {
                title: '前台设置'
              },
              component: () => import('@/views/system/web/SystemWeb.vue')
            },
            {
              name: 'SystemAdmin',
              path: '/system/admin',
              meta: {
                title: '后台设置'
              },
              component: () => import('@/views/system/admin/SystemAdmin.vue')
            },
            {
              name: 'SystemConfig',
              path: '/system/config',
              meta: {
                title: '配置管理'
              },
              component: () => import('@/views/system/config/SystemConfig.vue')
            }
          ]
        }
      ]
    }
  ]
})

export default router
