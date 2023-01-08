// 分页
window.onload = function () {
  console.log('可视化界面')
  new Vue({
    el: '#view',
    data: {
      detailData: [],
    },
    methods: {
      getdetailData: function (i) {
        axios({
          method: 'post',
          url: 'http://lxjoin.natapp1.cc/merchant/visual',
          data: {
            rid: 1,
            date: 2023,
          },
        })
          .then((res) => {
            console.log(res.data.data)
            // 分离出来数组对象中的每个属性
            let date = res.data.data.map((item) => {
              return item.date
            })
            let nums = res.data.data.map((item) => {
              return item.nums
            })
            let price = res.data.data.map((item) => {
              return item.totalPrices
            })
            console.log(date)
            this.detailData = res.data.data
            var myChart1 = echarts.init(
              document.getElementById('main1'),
              'vintage'
            )
            var myChart2 = echarts.init(
              document.getElementById('main2'),
              'vintage'
            )
            // 指定图表的配置项和数据
            let option1 = {
              title: {
                text: '自习室可视化客流量',
              },
              tooltip: {},
              legend: {
                data: ['客流量'],
              },
              xAxis: {
                data: date,
              },
              yAxis: {
                type: 'value',
                axisLabel: {
                  formatter: '{value}个',
                },
              },
              series: {
                name: '总人数',
                type: 'bar',
                data: nums,
              },
            }
            let option2 = {
              title: {
                text: '自习室月收入可视化',
              },
              tooltip: {},
              legend: {
                data: ['月收入'],
              },
              xAxis: {
                data: date,
              },
              yAxis: {
                type: 'value',
                axisLabel: {
                  formatter: '{value}元',
                },
              },
              series: {
                name: '总钱数',
                type: 'line',
                data: price,
              },
            }
            // 使用刚指定的配置项和数据显示图表。
            myChart1.setOption(option1)
            myChart2.setOption(option2)
          })

          .catch(function (err) {
            console.log(err)
          })
      },
    },
    mounted: function () {
      this.getdetailData()
    },
  })
}
