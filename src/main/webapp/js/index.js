var uid
$(function () {
    categroy()
    login()
    myCart()
    // changePassword()
})

// function item() {
//     $.ajax({
//         url: "http://127.0.0.1:8080/ssm_sun_mall_war/product/queryProduct",
//         success: function (data) {
//             console.log(data)
//             // listItem(data)//商品
//             categroy(data)
//         }
//     })
// }

function categroy() {
    var categroyStr = ""
    $.ajax({
        url:"http://127.0.0.1:8080/ssm_sun_mall_war/category/clist",
        success: function (data) {
            console.log(data)
            clear()
            for (var i in data) {
                console.log(data[i].id + ":" + data[i].name)
                categroyStr += `<a href='#c${data[i].id}' class='list-group-item'>${data[i].name}<span class='glyphicon glyphicon-menu-right gl-right'></span></a>`
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
                    <h3 class="thumbnail-h3" id="c${cid}">${cname}</h3>
                </div>
                <div class="col-sm-12">`
    $.ajax({
        url: "http://127.0.0.1:8080/ssm_sun_mall_war/product/queryProductByCid?cid=" + cid,
        success(data) {
            for (var i in data.list) {
                itemStr += `
                    <div class="col-sm-3" onclick="openDetail(${data.list[i].id})">
                        <div class="thumbnail thumbnail-border">
                            <img src="http://127.0.0.1:8080/upload/${data.list[i].mainImage}" alt="...">
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
                uid = data.data.id
                // $("#login a").html("欢迎你" + data.errorCode)
                // alert("欢迎你" + data.data.nikename)
                var str =
                    ` <ul class="dropdown-menu">
                        <li><a type="button" class="btn btn-link" href="html/orderList.html?uid=${uid}">订单</a></li>
                        <li>
                        <a type="button" class="btn btn-link" name="register" id="register"
                data-toggle="modal" data-target="#myModal">修改密码</a></li>
                        <li><a type="button" class="btn btn-link" href="http://127.0.0.1:8080/ssm_sun_mall_war/user/logout">退出</a></li>
                      </ul>`;
                $("#nav-right li:eq(0)").html(`<a href='#' class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                            aria-expanded="false">欢迎你:${data.data.nikename}<span></span><span class="caret"></span></a>` + str);
            }
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

function likeQuery() {
    let name=$("[name=name]").val()
    if (name.length==0 || name == "" || name == null || name == undefined){
        alert("请输入要搜索的名字")
        $("[name=name]").focus()
        return
    }else{
        var itemStr = `
                    <section class="item_box">
                        <div class="container">
                            <div class="col-sm-12">
                                <div class="row row-content">
                                    <div class="col-sm-12">
                                        <h3 class="thumbnail-h3">${name}</h3>
                                    </div>
                                    <div class="col-sm-12">`
        $.ajax({
            url:"http://127.0.0.1:8080/ssm_sun_mall_war/product/likeQuery?name="+name,
            success(data){
                $("#item_box").empty()
                for (var i in data.list) {
                    itemStr += `
                    <div class="col-sm-3" onclick="openDetail(${data.list[i].id})">
                        <div class="thumbnail thumbnail-border">
                            <img src="http://127.0.0.1:8080/upload/${data.list[i].name + "1.PNG"}" alt="...">
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
}
function clear() {
    $("#item_box").empty()
    $("#categroy").empty()
}
//验证原密码1.ajax,正则
var ok1 = false, ok2 = false, ok3 = false;
$(function () {
    $("[name=oldPassword]").blur(function () {
        let oldPassword = $(this).val();
        $.ajax({
            url: "http://127.0.0.1:8080/ssm_sun_mall_war/user/checkPassword",
            data: {
                password: oldPassword
            },
            type: "post",
            success: function (data) {
                if (200 == data.errorCode) {
                    ok1 = true;
                } else {
                    ok1 = false;
                    $("[name=oldPassword]").next().html("<font color='red'>与原密码不一致</font>");
                }

            }
        });
    });

    //验证新密码
    $("[name=newPaasword]").blur(function () {
        let newPassword = $(this).val();
        console.log("newPassword" + newPassword)
        let reg = /^[0-9a-zA-Z]{6,15}$/;
        if (reg.test(newPassword)) {
            $(this).next().html("新密码可用");
            ok2 = true;
        } else {
            $(this).next().html("新密码不可用");
            ok2 = false;
        }
    });


    //验证重复密码格式，是否与新密码相同
    $("[name=rePassword]").blur(function () {
        let rePassword = $(this).val();
        console.log("rePassword" + rePassword)
        let newPassword = $("[name=newPassword]").val();
        console.log("newPassword" + newPassword)
        let reg = /^\w{6,}$/;
        if (reg.test(rePassword)) {
            if (newPassword == rePassword) {
                $("[name=rePassword]").next().html("两次密码一致");
                ok3 = true;
            } else {
                $("[name=rePassword]").next().html("两次密码不一致");
                ok3 = false;
            }
        } else {
            $(this).next().html("<font color='red'>重复密码不可用</font>");
            ok3 = false;
        }
    });

    //提交表单
    $("#change").click(function () {
        alert("点击" + ok3 + ok2 + ok1 + $("[name=newPassword]").val())
        if (ok1 && ok3) {
            $.ajax({
                url: "http://127.0.0.1:8080/ssm_sun_mall_war/user/changePassword",
                data: {
                    newPassword: $("[name=newPassword]").val()
                },
                success(data) {
                    if (data.errorCode == 200) {
                        alert("更新成功，请重新登陆")
                        window.location.href = "html/login.html"
                    } else {
                        alert("输入有误")
                    }
                }
            })
        }
    });

});

