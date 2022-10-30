<script setup lang="ts">
import { articleDetail } from '@/mock/system'
import { useTocStore } from '@/stores/modules/toc'

const contentRef = ref()
const toc = ref()

const tocStore = useTocStore()

const buildToc = () => {
  nextTick(() => {
    const titleTag = ['H1', 'H2', 'H3']
    let titles = [] as any[]
    contentRef.value.childNodes.forEach((e: any, index: number) => {
      if (titleTag.includes(e.nodeName)) {
        const id = 'title-' + index
        const level = Number(e.nodeName.substring(1, 2))
        e.setAttribute('id', id)

        let node = {
          id,
          level,
          parent: null,
          children: [],
          title: e.innerHTML
        }

        if (titles.length > 0) {
          let lastNode = titles.at(-1)
          // 遇到子标题
          if (lastNode.level < node.level) {
            node.parent = lastNode
            lastNode.children.push(node)
          }
          // 遇到上一级标题
          else if (lastNode.level > node.level) {
            let parent = lastNode.parent
            while (parent) {
              if (parent.level < node.level) {
                parent.children.push(node)
                node.parent = parent
                break
              }
              parent = parent.parent
            }
          }
          // 遇到平级
          else if (lastNode.parent) {
            node.parent = lastNode.parent
            lastNode.parent.children.push(node)
          }
        }

        titles.push(node)
      }
    })
    toc.value = titles.filter((e) => e.parent === null)
    tocStore.handleToc(toc.value)
  })
}
buildToc()
</script>

<template>
  <div class="detail page-content">
    <PageHeader
      :title="articleDetail.title"
      :date="articleDetail.date"
      :view="articleDetail.view"
      :category="articleDetail.categoryName"
    />

    <div ref="contentRef" class="content article-content" v-html="articleDetail.content"></div>

    <div class="footer">
      <div class="tags">
        <span v-for="(item, index) in articleDetail.tags" :key="index">{{ item.name }}</span>
      </div>
      <div class="nav">
        <div><entypo:triangle-left /> {{ articleDetail.prev.title }}</div>
        <div>{{ articleDetail.next.title }} <entypo:triangle-right /></div>
      </div>
    </div>

    <Comment />
  </div>
</template>

<style lang="scss" scoped>
.detail {
  position: relative;
}

.nav,
.nav > div {
  display: flex;
  align-items: center;
}

.content {
  font-size: 1.125rem;
  line-height: 1.6;
  text-align: justify;
}

.tags {
  padding-bottom: 1rem;
  border-bottom: 1px solid var(--gray);
  span {
    margin-right: 6px;
    padding: 4px 10px;
    background-color: var(--dark);
    color: var(--white);
  }
}

.nav {
  margin: 1rem 0;
  justify-content: space-between;
  font-size: 1.125rem;
}
</style>

<style lang="scss">
.article-content {
  p {
    margin-bottom: 1rem;
  }
}
</style>
