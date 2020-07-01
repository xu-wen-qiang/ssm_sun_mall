var userid
var i = 0;
$(function () {
    userid = $.query.get("userid")
    item()
    setTimeout(function () {
        add_min()
    },1000)
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
        url: "http://127.0.0.1:8080/ssm_sun_mall_war/cart/queryAll?uid=" + userid,
        success(data) {
            if (data.errorCode == 200) {
                for (let i in data.data) {
                    var tolPrc = (data.data[i].product.price) * (data.data[i].quantity);
                    tbody += `
                            <tr>
                            <td>
                              <div class="checkbox">
                                <label>
                                  <input type="checkbox" onclick="updateSelection(this)" name="ids" class="id" value="${data.data[i].id}">
                                </label>
                              </div>
                            </td>
                            <td name="name">${data.data[i].product.name}</td>
                            <td name="detail">${data.data[i].product.detail}</td>
                            <td name="mianImage"><img style="width: 100%;" src="http://127.0.0.1:8080/upload/${data.data[i].product.mainImage}"></td>
                            <td name="price">${data.data[i].product.price}</td>
                            <td>
                            <input class="min" name="min" type="button" value="-" />
                            <input style="width: 20px" class="text_box" name="quantity" type="text" value="${data.data[i].quantity}" />
                            <input class="add" name="add" type="button" value="+" />
                            </td>   
                            <td>
                                <a class="btn btn-primary btn-success" href="javascript:void(0)" onclick="pay(${data.data[i].id})">购买</a>
                            </td>
                            </tr>`
                }
                $("#tbody").append(tbody)
            }
            console.log(data)
        }
    })
}

function add_min() {
    console.log("...........")
    // alert("")
    $(".add").on("click",function(){
        // alert("点击+")
        var t=$(this).parent().find('input[class*=text_box]');
        t.val(parseInt(t.val())+1)
        let id = $(this).parent().parent().find("input[class*=id]").val()
        // alert(id)
        $.ajax({
            url:"http://127.0.0.1:8080/ssm_sun_mall_war/cart/updateQuantity",
            data:{
                id:id,
                quantity:t.val()
            },
            success(data){
              if (data.errorCode == 200){

              }  else if (data.errorCode == 504){
                  alert("添加失败")
                  t.val(parseInt(t.val())-1)

              }
            }
        })
    })
    $(".min").on("click",function(){
        var t=$(this).parent().find('input[class*=text_box]');
        t.val(parseInt(t.val())-1)
        let id = $(this).parent().parent().find("input[class*=id]").val()
        if(parseInt(t.val())<=0){
            t.val(1);
        }
        $.ajax({
            url:"http://127.0.0.1:8080/ssm_sun_mall_war/cart/updateQuantity",
            data:{
                id:id,
                quantity:t.val()
            },
            success(data){
                if (data.errorCode == 200){

                }  else if (data.errorCode == 504){
                    alert("添加失败")
                    t.val(parseInt(t.val())+1)

                }
            }
        })
    })
}

function pay(cartId) {
    var username
    var phone
    var province10
    var city10
    var district10
    var detailed_address
    var address
    $("#distpicker5").distpicker('destroy');
    $("#distpicker5").distpicker({
        province: '省份名',
        city: '城市名',
        district: '区名',
        autoSelect: true,
        placeholder: false
    });
    $('#myModal').modal()
    $("#gopay").click(function () {
        username = $("#username").val()
        phone = $("#phone").val()
        province10 = $("#province10").val()
        city10 = $("#city10").val()
        district10 = $("#district10").val()
        detailed_address = $("#detailed_address").val()
        if (judge(province10)){
            alert("请选择省")
            return;
        }
        if (judge(city10)){
            alert("请选择城市")
            return;
        }
        if (judge(district10)){
            alert("请选择区")
        }
        if (judge(detailed_address)){
            alert("请填写详细地址")
            return;
        }
        address = province10 + "," + city10 + "," + district10 + "," + detailed_address
        if (judge(username)) {
            alert("请填写收货人")
            return;
        } else if (judge(phone)) {
            alert("请填写联系方式")
            return;
        } else if (judge(address)) {
            alert("请填写收货地址")
            return;
        } else {
            window.location.href = "http://127.0.0.1:8080/ssm_sun_mall_war/order/alipay?" +
                "cartId=" + cartId +
                "&address=" + address +
                "&phone=" + phone +
                "&username=" + username
        }
    })

}
function judge(arg) {
    if (arg == null || arg == undefined || arg.length == 0 || arg == ""){
        return true
    }else {
        return false
    }
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
}
//批量删除事件
function deleteList() {
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
        alert("请选择要移除的商品!");
        return;
    }

    if (window.confirm("确认删除吗?")) {
        $.ajax({//利用ajax发出请求
            type: "post",//请求类型
            url: "http://127.0.0.1:8080/ssm_sun_mall_war/cart/deleteList?ids=" + ids + "&uid=" + userid,
            success: function (data) {//删除成功后，后台会返回一个"ok";
                if (data.errorCode == 200) {
                    alert("删除成功");//返回ok后弹出一个对话框。
                    location.href = "myCart.html?userid=" + userid;//相当于刷新界面
                } else {
                    alert("删除失败");
                }
            }
        });
    }
}
