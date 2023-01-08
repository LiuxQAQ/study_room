// 分页
window.onload = function () {
  console.log('beigin')
  new Vue({
    el: '#app',
    data: {
      lists: [],
      page: {
        rows: 1, //总条数
        pageNum: 1, //页码
        pageTotal: 1, //总页数
        pageSize: 5, //每页的条数
      },
    },
    methods: {
      getLists: function (i) {
        this.page.pageNum = i || this.page.pageNum
        axios({
          method: 'get',
          url: 'http://joinstudio.nat300.top/management/orderList',
          params: {
            pageNum: this.page.pageNum,
            pageSize: this.page.pageSize,
            state: 1,
          },
        })
          .then((res) => {
            console.log(res.data.data.total)
            this.lists = res.data.data.rows
            this.page.rows = res.data.data.total
            this.page.pageTotal = Math.ceil(this.page.rows / this.page.pageSize)
            console.log(this.page.pageTotal)
          })
          .catch(function (err) {
            console.log('请求失败')
          })
      },

      curPage(ind) {
        if (ind == '...') return
        this.getLists(ind)
      },
      prePage: function () {
        if (this.page.pageNum > 1) {
          this.page.pageNum--
          this.getLists(this.page.pageNum)
        }
      },
      nextPage: function () {
        if (this.page.pageNum < this.page.pageTotal) {
          this.page.pageNum++
          this.getLists(this.page.pageNum)
        }
      },
    },
    computed: {
      num: function () {
        return function (i) {
          return (this.page.pageNum - 1) * this.page.pageSize + i + 1
        }
      },
      pages() {
        let { pageNum: n, pageTotal: total } = this.page
        if (total < 10) return total
        if (n <= 5) {
          return [1, 2, 3, 4, 5, 6, '...', total]
        } else if (n >= total - 5) {
          return [
            1,
            '...',
            total - 5,
            total - 4,
            total - 3,
            total - 2,
            total - 1,
            total,
          ]
        } else {
          return [1, '...', n - 2, n - 1, n, n + 1, n + 2, '...', total]
        }
      },
    },
    mounted: function () {
      this.getLists()
    },
  })
}
