/* 以json的格式提交登录传参 */
var flag = false
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
                data: {
                    username: username,
                    password: password
                },
                //点击登录以后拿到数据
                success: function (data) {

                    if (data.errorCode == 201) {
                        alert(data.errorMsg)
                    } else if (data.errorCode == 200) {
                        if (data.data.role == 0) {
                            window.location.href = "http://127.0.0.1:8080/ssm_sun_mall_war/html/showProduct.html"
                                // + "?userid=" + data.data.id + "&nikename=" + data.data.nikename
                        } else {
                            window.location.href = "http://127.0.0.1:8080/ssm_sun_mall_war/index.html"
                                // +"?userid=" + data.data.id + "&username=" + data.data.nikename
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
    reg()
    $("#save").click(function () {
        if (flag){
            $.ajax({
                type: "POST",   //提交的方法
                url: "http://127.0.0.1:8080/ssm_sun_mall_war/user/register", //提交的地址
                data: $('#ajaxForm').serialize(),// 序列化表单值
                async: false,
                error: function (request) {  //失败的话
                    console.log(request)
                },
                success: function (data) {  //成功
                    console.log(data);  //就将返回的数据显示出来
                    if (data.errorCode == 201) {
                        alert(data.errorMsg)
                    }
                    if (data.errorCode == 200) {
                        window.location.href = "http://127.0.0.1:8080/ssm_sun_mall_war/html/login.html"
                    }
                }
            });
        }else {
            alert("输入错误")
        }

    });
})
function reg() {
    $("#username").blur(function () {
        //4到16位（字母，数字，下划线，减号）
        var reg_username = /^[a-zA-Z0-9_-]{4,16}$/;
        var username=$(this).val()
        if (reg_username.test(username)){
            flag=true
            $(this).next().html("<font color='red'>用户名合法</font>")
        }else {
            $(this).next().html("<font color='red'>请输入合法用户名</font>")
            $(this).focus()
            flag=false
        }
    })
    // 电话验证
    $("#phone").blur(function () {
        //13、14、15、17、18 11位
        var reg_phone = /0?(13|14|15|17|18)[0-9]{9}/;
        if (reg_phone.test($("#phone").val())){
            flag = true
            $(this).next().html("<font color='red'>电话号码合法</font>")
        }else {
            $(this).next().html("<font color='red'>请输入合法电话号码</font>")
            $(this).focus()
            flag = false
        }
    })
    // 密码验证
    $("#password").blur(function () {
        var password=$(this).val();
        // 任意字符 6-15位
        var reg_password=/^\w{6,15}$/;
        if(reg_password.test(password)){
            $(this).next().html("密码可用");
            flag=true;
        }else {
            $(this).next().html("密码不可用");
            $(this).focus()
            flag=false;

        }
    })
    //验证重复密码格式，是否与新密码相同
    $("#repassword").blur(function () {
        var repassword=$(this).val();
        var password=$("#password").val();
        var reg_repassword=/^\w{6,15}$/;
        if(reg_repassword.test(password)){
            if (repassword==password){
                $(this).next().html("<font color='red'>两次密码一致</font>");
                flag=true;
            } else {
                $(this).next().html("<font color='red'>两次密码不一致</font>");
                $(this).focus()
                flag=false;
            }
        }else {
            $(this).next().html("<font color='red'>重复密码不可用</font>");
            $(this).focus()
            flag=false;
        }
    });
    // 邮箱验证
    $("email").blur(function () {

        var reg_email = /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;
        var email = $("#email").val();
        if (reg_email.test(email)){
            $(this).next().html("<font color='red'>邮箱正确</font>")
            $(this).focus()
            flag=true
        }else {
            $(this).next().html("<font color='red'>请输入合法邮箱</font>")
            $(this).focus()
            flag=false
        }
    })
}