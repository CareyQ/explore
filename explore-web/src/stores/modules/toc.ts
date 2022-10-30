export const useTocStore = defineStore('toc-store', {
  state: () => ({
    toc: [] as any
  }),

  actions: {
    handleToc(toc: any[]) {
      this.toc = toc
    }
  }
})
