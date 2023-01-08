// 分页
window.onload = function () {
  console.log('beigin')
  new Vue({
    el: '#details',
    data: {
      id: 1,
      merchant: {},
      room: {},
    },
    methods: {
      getId: function () {
        var that1 = this
        var url = location.search
        var id = url.split('mid=')[1].split('&')[0]
        var rid = url.split('rid=')[1]
        console.log(id)
        console.log(rid)
        // 第2个接口
        axios({
          method: 'get',
          url: `http://joinstudio.nat300.top/management/${id}`,
        })
          .then((res) => {
            console.log('第2个接口')
            console.log(res.data.data)
            that1.merchant = res.data.data.merchant
            that1.room = res.data.data.room
            that1.setData({
              merchant: res.data.data.merchant,
              room: res.data.data.room,
            })
          })
          .catch(function (err) {
            console.log('请求失败')
          })
      },
      pass: function (i) {
        console.log(i) //此时i为1,2
        var url = location.search
        var id = url.split('mid=')[1].split('&')[0]
        var rid = url.split('rid=')[1]

        axios({
          method: 'put',
          url: `http://joinstudio.nat300.top/management`,
          data: {
            id: id,
            rid: rid,
            state: i,
          },
        }).then((res) => {
          console.log('第3个接口成功')
          console.log(res)
          const div = document.createElement('div')
          document.body.appendChild(div)
          div.innerHTML = '提交成功'
          div.className = 'popupStyle'
          div.style.display = 'block'
          setTimeout(() => {
            div.remove()
          }, 1000)
        })
      },
    },

    mounted: function () {
      this.getId()
    },
  })
}
