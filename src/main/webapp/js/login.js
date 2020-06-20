/* 以json的格式提交登录传参 */
$(function () {
    console.log("执行登陆js")
    $("#login").click(function () {
        var username = document.getElementById("inputUsername").value;
        var password = document.getElementById("inputPassword").value;
        if (username.trim().length == 0) {
            alert("用户名不能为空");
            obj.focus();
        } else if (password.trim().length == 0) {
            alert("密码不能为空");
            obj.focus();
        } else {
            $.ajax({
                type: 'post',
                //提交路径
                url: 'http://127.0.0.1:8080/ssm_sun_mall_war/user/login',
                //声明为json格式
                // dataType: "JSON",
                //转为json格式
                data: {
                    username: username,
                    password: password
                },
                //点击登录以后拿到数据
                success: function (data) {

                    console.log(data)
                    console.log(data.data)
                    console.log(data.data.nikename)
                    let nikename = data.data.nikename;
                    // alert(data.data)
                    $.cookie("nikename", nikename);
                    // $.cookie("userid",data.data.id)
                    if (data.errorCode == 201) {
                        alert(data.errorMsg)
                    } else if (data.errorCode == 200) {
                        if (data.data.role == 0) {
                            window.location.href = "http://127.0.0.1:8080/ssm_sun_mall_war/pages/showProduct"
                        } else {

                        }
                    }
                }, error: function (e) {
                    console.log("error" + e);
                }
            });
        }
    });
})

$(function () {
    $("#save").click(function () {
        $.ajax({
            type: "POST",   //提交的方法
            url: "http://127.0.0.1:8080/ssm_sun_mall_war/user/register", //提交的地址
            data: $('#ajaxForm').serialize(),// 序列化表单值
            async: false,
            error: function (request) {  //失败的话
                console.log(request)
                alert("Connection error");
            },
            success: function (data) {  //成功
                console.log(data);  //就将返回的数据显示出来
                if (data.errorCode == 201) {
                    alert(data.errorMsg)
                }
                if (data.errorCode == 200) {
                    alert(data.errorMsg)
                    window.location.href = "http://127.0.0.1:8080/ssm_sun_mall_war/login.jsp"
                }
            }
        });
    });
})
