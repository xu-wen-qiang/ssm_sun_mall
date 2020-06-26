$(function () {
    categroy()
    login()
    myCart()
})

function item() {
    $.ajax({
        url: "http://127.0.0.1:8080/ssm_sun_mall_war/product/queryProduct",
        success: function (data) {
            console.log(data)
            // listItem(data)//商品
            categroy(data)
        }
    })
}

function categroy() {
    var categroyStr = ""
    $.ajax({
        url: "http://127.0.0.1:8080/ssm_sun_mall_war/category/clist",
        success: function (data) {
            console.log(data)
            for (var i in data) {
                console.log(data[i].id + ":" + data[i].name)
                categroyStr += "<a href='#' class='list-group-item'>" + data[i].name + "<span class='glyphicon glyphicon-menu-right gl-right'></span></a>"
                listItem(data[i].id, data[i].name)
            }
            $("#categroy").append(categroyStr)
        }
    })
}

function listItem(cid, cname) {
    console.log(cid + ":" + cname)
    var itemStr = `
<section class="item_box">
    <div class="container">
        <div class="col-sm-12">
            <div class="row row-content">
                <div class="col-sm-12">
                    <h3 class="thumbnail-h3">${cname}</h3>
                </div>
                <div class="col-sm-12">`
    $.ajax({
        url: "http://127.0.0.1:8080/ssm_sun_mall_war/product/queryProductByCid?cid=" + cid,
        success(data) {
            for (var i in data.list) {
                itemStr += `
                    <div class="col-sm-3" onclick="openDetail(${data.list[i].id})">
                        <div class="thumbnail thumbnail-border">
                            <img src="http://127.0.0.1:8080/ssm_sun_mall_war/upload/${data.list[i].name + "1.PNG"}" alt="...">
                            <div class="caption">
                                <h3 class="text-center h4">${data.list[i].name}</h3>
                                <p class="text-center p">${data.list[i].detail}</p>
                                <p class="text-center p-color">${data.list[i].price}</p>
                            </div>
                        </div>
                    </div>`
            }
            itemStr += ` </div></div></div></div></section>`
            $("#item_box").append(itemStr)
        }
    })
}

function openDetail(itemId) {
    // alert(itemId)
    window.location.href = "html/detail.html?itemId=" + itemId
}

function login() {
    $.ajax({
        url: "http://127.0.0.1:8080/ssm_sun_mall_war/user/isLogin",
        success(data) {
            if (data.errorCode == 301) {

            }
            if (data.errorCode == 200) {
                $("#login a").html("欢迎你" + data.errorCode)
            }
            a
        }, error(e) {
            console.log("error" + e)
            // alert("login"+e)
        }
    })
}

function myCart() {
    $("#myCart").click(function () {
        $.ajax({
            url: "http://127.0.0.1:8080/ssm_sun_mall_war/user/isLogin",
            success(data) {
                if (data.errorCode == 301) {
                    alert(data.errorMsg + "请登录")
                }
                if (data.errorCode == 200) {
                    window.location.href = "html/myCart.html?userid=" + data.data.id
                }
            }, error(e) {
                console.log("error" + e)
                alert("login" + e)
            }

        })
    })

}