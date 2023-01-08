// 分页
window.onload = function () {
  console.log('beigin')
  new Vue({
    el: '#all',
    data: {
      dataLists: [],
    },
    methods: {
      getLists: function (i) {
        // 第一个接口
        var that1 = this
        axios({
          method: 'get',
          url: 'http://joinstudio.nat300.top/management',
        })
          .then((res) => {
            console.log('第一个接口')
            console.log(res)
            console.log(res.data.data)
            this.dataLists = res.data.data
          })
          .catch(function (err) {
            console.log('请求失败')
          })
      },
      detail: function (i, j) {
        console.log(i)
        console.log(j)

        window.location.href = '../pages/details.html?mid=' + i + '&rid=' + j
      },
    },

    mounted: function () {
      this.getLists()
    },
  })
}
