var userid = $.query.get("userId")
alert(userid)
var i = 0;
$(function () {
    item()
    $("#ifAll").on("click", function () {
        if (i == 0) {
            //把所有复选框选中
            $("td :checkbox").prop("checked", true);
            i = 1;
        } else {
            $("td :checkbox").prop("checked", false);
            i = 0;
        }
    });
})

function item() {
    var tbody = ""
    $.ajax({
        url: "http://127.0.0.1:8080/ssm_sun_mall_war/cart/queryAll?userId=" + userid,
        success(data) {
            if (data.errorCode == 200) {
                for (let i in data.data) {
                    var tolPrc = (data.data[i].product.price) * (data.data[i].quantity);
                    tbody += `
                            <tr>
                            <td>
                              <div class="checkbox">
                                <label>
                                  <input type="checkbox" name="ids" value="${data.data[i].id}">
                                </label>
                              </div>
                            </td>
                            <td name="name">${data.data[i].product.name}</td>
                            <td name="detail">${data.data[i].product.detail}</td>
                            <td name="mianImage">${data.data[i].product.mainImage}</td>
                            <td name="price">${data.data[i].product.price}</td>
                            <td>
                            <input type="button" value="+" id="add" "/>
                            <input type="text" value="${data.data[i].quantity}" class="text_box" id="text" onblur="change()"/>
                            <input type="button" value="-" id="min"/>
                            </td>
                            <td>#</td>
                            </tr>`
                }
                $("#tbody").append(tbody)
            }
            console.log(data)
            alert(data)
        }
    })
}

//列表复选框
//定义向后台传输的所有id
var ids = '';

//复选框的更改,如:选择,取消选择
function updateSelection(selectFlag) {
    //清空
    ids = '';
    //页面input的name属性
    var selectIds = document.getElementsByName("ids");
    for (var i = 0; i < selectIds.length; i++) {
        if (selectIds[i].checked) {
            //取得该属性的值id
            var id = $(selectIds[i]).val();
            //拼接ids字符串
            if (ids == '') {
                ids = id;
            } else {
                ids += ',' + id;
            }
        }
    }
    alert(ids)
}

//批量删除事件
function batchDeleYyb() {
    //清空
    ids = '';
    //页面input的name属性
    var selectIds = document.getElementsByName("ids");
    for (var i = 0; i < selectIds.length; i++) {
        if (selectIds[i].checked) {
            //取得该属性的值id
            var id = $(selectIds[i]).val();
            //拼接ids字符串
            if (ids == '') {
                ids = id;
            } else {
                ids += ',' + id;
            }
        }
    }

    if (ids.length == 0) {
        alert("请选择要删除的app!");
        return;
    }

    if (window.confirm("确认删除吗?")) {
        $.ajax({//利用ajax发出请求
            type: "post",//请求类型
            url: "http://127.0.0.1:8080/ssm_sun_mall_war/cart/deleteList?ids=" + ids + "&uid=" + userid, //向Controller里的batchDeleYyb传输ids
            // data: {
            //     ids: ids,
            //     uid:userid
            // },     //键值对
            success: function (data) {//删除成功后，后台会返回一个"ok";
                if (data.errorCode == 200) {
                    alert("删除成功");//返回ok后弹出一个对话框。
                    location.href = "myCart.html";//相当于刷新界面
                } else {
                    alert("删除失败");
                }
            }
        });
    }
    alert(ids)
}

//全选/反选操作
//全选

