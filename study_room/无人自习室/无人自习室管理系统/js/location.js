console.log('1111,location')
getStores(1)
var submit = document.getElementById('submit')
var jw = document.getElementById('jw').value
console.log(jw.split(','))
var licence = document.getElementById('licence')
var licenceUrl = 0
licence.onchange = function () {
  console.log(this.files[0])
  let formData = new FormData()
  formData.append('img', this.files[0])
  // formData.append("id",1);
  console.log(formData)
  var xhr = new XMLHttpRequest()
  xhr.open('post', 'http://lxjoin.natapp1.cc/upload', true)
  // xhr.setRequestHeader("Content-Type", "multipart/form-data");
  xhr.send(formData)
  xhr.onreadystatechange = function () {
    if (xhr.status === 200) {
      var res = JSON.parse(xhr.responseText)
      console.log(res)
      licenceUrl = res.data
    }
  }
}
var photoUrl = 0
var photo = document.getElementById('photo')
photo.onchange = function () {
  console.log(this.files[0])
  let formData = new FormData()
  formData.append('img', this.files[0])
  // formData.append("id",1);
  console.log(formData)
  var xhr = new XMLHttpRequest()
  xhr.open('post', 'http://lxjoin.natapp1.cc/upload', true)
  // xhr.setRequestHeader("Content-Type", "multipart/form-data");
  xhr.send(formData)
  xhr.onreadystatechange = function () {
    if (xhr.status === 200) {
      var res = JSON.parse(xhr.responseText)
      console.log(res)
      photoUrl = res.data
    }
  }
}
var seatUrl = 0
var seatPhoto = document.getElementById('seatPhoto')
seatPhoto.onchange = function () {
  console.log(this.files[0])
  let formData = new FormData()
  formData.append('img', this.files[0])
  // formData.append("id",1);
  console.log(formData)
  var xhr = new XMLHttpRequest()
  xhr.open('post', 'http://lxjoin.natapp1.cc/upload', true)
  // xhr.setRequestHeader("Content-Type", "multipart/form-data");
  xhr.send(formData)
  xhr.onreadystatechange = function () {
    if (xhr.status === 200) {
      var res = JSON.parse(xhr.responseText)
      console.log(res)
      seatUrl = res.data
      console.log(seatUrl)
    }
  }
}
submit.onclick = function () {
  var rname = document.getElementById('rname').value
  var rtel = document.getElementById('rtel').value
  var sel1 = document.getElementById('sel1').value
  var sel2 = document.getElementById('sel2').value
  var sel3 = document.getElementById('sel3').value
  var address = document.getElementById('address').value
  var jw = document.getElementById('jw').value
  var rseat = document.getElementById('rseat').value
  var rmoney = document.getElementById('rmoney').value
  var longitude = jw.split(',')[0]
  var latitude = jw.split(',')[1]
  var desc = document.getElementById('desc').value
  var data = {
    name: rname,
    phone: rtel,
    city: sel2,
    district: sel3,
    // sel : sel2+sel3,
    address: address,
    // jw : jw,
    longitude: longitude,
    latitude: latitude,
    seat: rseat,
    cost: rmoney,
    roomDescribe: desc,
    licence: licenceUrl,
    photo: photoUrl,
    seatPhoto: seatUrl,
    mId: 1,
    openingHours: '8:00-24:00',
  }
  console.log('纬度：longitude', jw.split(',')[0])
  console.log('经度：latitude', jw.split(',')[1])
  console.log(data)
  var str = JSON.stringify(data)
  var xhr = new XMLHttpRequest()
  xhr.open('post', 'http://lxjoin.natapp1.cc/merchant/createRoom', true)
  xhr.setRequestHeader('Content-Type', 'application/jsonp;charset=UTF-8')
  xhr.send(str)
  xhr.onreadystatechange = function () {
    if (xhr.status === 200) {
      var res = JSON.parse(xhr.responseText)
      console.log(res)
      if (res.code === 200) {
        const div = document.createElement('div')
        document.body.appendChild(div)
        div.innerHTML = '创建自习室成功'
        div.className = 'popupStyle'
        div.style.display = 'block'
        setTimeout(() => {
          div.remove()
        }, 1000)
      }
    }
  }
}

function getStores(data) {
  console.log('getStores', data)
  var newData = {
    mId: 1,
  }
  var str = JSON.stringify(newData)
  var xhr = new XMLHttpRequest()
  xhr.open('post', 'http://lxjoin.natapp1.cc/merchant', true)
  xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8')
  xhr.withCredentials = true
  xhr.send(str)
  xhr.onreadystatechange = function () {
    if (xhr.status === 200) {
      var res = JSON.parse(xhr.responseText)
      console.log(res)
      if (res.code == 200) {
        console.log(res.data)
        var List = res.data
        var showTab = document.getElementById('showTab')
        var show = ''
        show += `
                        <thead>
                            <tr>
                                <th class="numTitle">序号</th>
                                <th class="title">名称</th>
                                <th class="address">地址</th>
                                <th class="seat">座位</th>
                                <th class="request">状态</th>
                            </tr>
                        </thead>
                    `
        for (var i = 0; i < List.length; i++) {
          show += `
                        <tr>
                            <td class="num">${i + 1}</td>
                            <td class="title">${List[i].name}</td>
                            <td class="address">${List[i].address}</td>
                            <td class="seat">${List[i].seat}</td>
                            <td class="request" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
                                <button class="item_delete" data-id="${
                                  List[i].id
                                }" data-state="${List[i].state}">申请</button>
                            </td>
                        </tr>
                        `
        }
        showTab.innerHTML = show
        var newdata = {
          storeId: 1,
          stroeState: 0,
        }
        var delete1 = document.querySelectorAll('.item_delete')
        for (var i = 0; i < List.length; i++) {
          if (List[i].state == 0) {
            delete1[i].innerHTML = '申请成功'
          } else if (List[i].state == 2) {
            delete1[i].innerHTML = '正在申请'
          } else if (List[i].state == 3) {
            delete1[i].innerHTML = '等待申请'
          } else {
            delete1[i].innerHTML = '申请失败'
          }
        }
        for (var i = 0; i < delete1.length; i++) {
          // var store_state = delete1[i].getAttribute("data-state");
          delete1[i].onclick = function () {
            var storeID = this.getAttribute('data-id')
            var storeState = this.getAttribute('data-state')
            newdata = {
              storeId: storeID,
              storeState: storeState,
            }
            console.log(newdata)
            request(newdata)
          }
        }
      } else {
        console.log('展示失败')
        // var el = document.createElement("div");
        // el.setAttribute("class", "warningSuccess");
        // el.innerText = res.message;
        // document.body.appendChild(el);
        // el.classList.add("bounce-enter-active");
        // // warningAll.style.display = 'block';
        // setTimeout(() => {
        //     console.log("setTime");
        //     el.classList.remove("bounce-enter-active");
        //     el.classList.add("bounce-leave-active");
        //     // warningAll.style.display = 'none';
        // }, 2000);
      }
    }
  }
}

function request(data) {
  console.log('点击申请', data.storeId)
  if (data.storeState == 3) {
    var newda = {
      id: data.storeId,
    }
    console.log('发送申请的id', newda)
    var str = JSON.stringify(newda)
    var xhr = new XMLHttpRequest()
    xhr.open('post', 'http://lxjoin.natapp1.cc/merchant/applyRoom', true)
    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8')
    xhr.withCredentials = true
    xhr.send(str)
    xhr.onreadystatechange = function () {
      if (xhr.status === 200) {
        var res = JSON.parse(xhr.responseText)
        console.log(res)
        if (res.code == 200) {
          alert(res.msg)
          getStores(1)
        }
      }
    }
  } else {
    alert('您已申请过或者申请失败！')
  }
}
