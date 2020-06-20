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
                    SetCookie("nikename", nikename)
                    alert(data.data)
                    // $.cookie("nikename", nikename);
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

function SetCookie(name, value)//两个参数，一个是cookie的名子，一个是值
{
    // var Days = 30; //此 cookie 将被保存 30 天
    // var exp  = new Date();    //new Date("December 31, 9998");
    // exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "=" + escape(value);//+ ";expires=" + exp.toGMTString()
}

function getCookie(name)//取cookies函数
{
    var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if (arr != null) return unescape(arr[2]);
    return null;

}

function delCookie(name)//删除cookie
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null) document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}