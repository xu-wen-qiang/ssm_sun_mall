$(function () {
    $("#save").click(function () {
        $.ajax({
            type: "POST",   //提交的方法
            url:"http://127.0.0.1:8080/ssm_sun_mall_war/product/upd", //提交的地址
            data:$('#ajaxForm').serialize(),// 序列化表单值
            async: false,
            error: function(request) {  //失败的话
                console.log(request)
                alert("Connection error");
            },
            success: function(data) {  //成功
                alert("更新成功")
                window.location.href = "http://127.0.0.1:8080/ssm_sun_mall_war/pages/showProduct"
            }
        });
    });
})