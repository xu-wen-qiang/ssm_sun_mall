var ids = ""
var i = 0
// 页面初始化获取数据
$(function () {
    category()
    item()
    updSave()
    isLogin()
})

function isLogin() {
    $.ajax({
        url:"http://127.0.0.1:8080/ssm_sun_mall_war/user/isLogin",
        success(data){
            if (data.errorCode == 200){
                $("#nikename").append(data.data.nikename)
            }else if(data.errorCode == 301){
                alert(data.errorMsg)
                window.location.href="http://127.0.0.1:8080/ssm_sun_mall_war/html/login.html"
            }
        }
    })
}

// 获取种类列表
function category() {
    var htmlStr = '';
    var categoryStr = '';
    $.ajax({
        url: "http://127.0.0.1:8080/ssm_sun_mall_war/category/clist",
        success: function (data) {
            console.log(data)
            for (var i in data) {
                htmlStr += "<li><a href='javascript:cItem(" + data[i].id + ")')>" + data[i].name + "</a></li>"
                categoryStr += "<option value=" + data[i].id + ">" + data[i].name + "</option>"
            }
            $('.category').append(htmlStr);
            $('#categoryId').append(categoryStr);
        }
    })
}

// 通过类别id查询商品
function cItem(cid) {
    console.log("cItem" + (cid))
    $.ajax({
        type: 'post',
        url: "http://127.0.0.1:8080/ssm_sun_mall_war/product/queryProductByCid?cid=" + cid,
        // data:{
        //     cid:cid
        // },
        success: function (data) {
            console.log(data)
            // window.location.href = "http://127.0.0.1:8080/ssm_sun_mall_war/pages/showProduct"
            clear();
            listItem(data)
            pages(data)
        }
    })
}

// 商品&分页插件展示
function item() {
    $.ajax({
        url: "http://127.0.0.1:8080/ssm_sun_mall_war/product/queryProduct",
        success: function (data) {
            console.log(data)
            listItem(data)
            pages(data)
        }
    })
}

function itemByPn(pn) {
    $.ajax({
        url: "http://127.0.0.1:8080/ssm_sun_mall_war/product/queryProduct?pn=" + pn,
        success: function (data) {
            console.log(data)
            clear();
            listItem(data)
            pages(data)
        }
    })
}

// 商品展示
function listItem(data) {
    // alert(window.location.href)
    var htmlStr = '';
    let uploadUrl = `http://127.0.0.1:8080/upload/`;
    for (var i in data.list) {
        htmlStr += "<tr>" +
            "<td>" +
            "<div class=\"checkbox\">\n" +
            "<label>\n" +
            "<input type=\"checkbox\" onclick=\"updateSelection(this)\" name=\"ids\" value=\""+data.list[i].id+"\">\n" +
            "</label>\n" +
            "</div></td>"+
            "<td>" + data.list[i].name + "</td><td>" + data.list[i].price + "</td>" +
            "<td><img title='" + data.list[i].name + "'style='width: 100%;' src=" + uploadUrl + data.list[i].mainImage +"></td>" +
            "<td><img title='" + data.list[i].subtitle + "'style='width: 100%;' src=" + uploadUrl + data.list[i].subImages +"></td>" +
            "<td>" + data.list[i].detail + "</td>" +
            "<td><a href=\"javascript:del(" + data.list[i].id + ")\")>" +
            "<button type=\"button\" class=\"btn btn-danger btn-lg\" onclick=\"return confirm('确定要删除信息吗？') \">" +
            "<span class=\"glyphicon glyphicon-trash\"></span>删除</button></a></td>" +
            "<td>" +

            "<a href=\"javascript:upd(" + data.list[i].id + ")\">" +
            "<button type=\"button\" class=\"btn btn-primary btn-lg\"" +
            "data-toggle=\"modal\" data-target=\"#myUpdModal\">" +
            "<span class=\"glyphicon glyphicon-plus\"></span>更新</button>" +
            "</a></td>" +
            "</tr>"
    }

    $('.item tbody').append(htmlStr);
}

// 分页插件
function pages(data) {

    let hasNextPageStr = ""
    let navigatepageNumsStr = ""
    let hasPreviousPageStr = ""
    let Str = "<div class='col-md-6'><h4 style='margin: 0 0 0 38%;'>当前第" + data.pageNum + "页，共" + data.pages + "页，共" + data.total + "条记录数</h4></div>" +
        "<div class='col-md-6'><ul class='pagination pagination-lg'>" +
        "<li><a href='javascript:itemByPn(1)'>首页</a></li>"
    if (data.hasPreviousPageStr) {
        hasPreviousPageStr +=
            "<li><a href='javascript:itemByPn(" + data.pageNum - 1 + ")'aria-label='Previous'><span aria-hidden=\"true\">&laquo;</span></a></li>"

    }

    for (var i = 0; i < (data.navigatepageNums).length; i++) {
        if (data.navigatepageNums[i] == data.pageNum) {
            navigatepageNumsStr +=
                "<li class='active'><a href='javascript:itemByPn(" + data.navigatepageNums[i] + ")'>" + data.navigatepageNums[i] + "</a></li>"
        }
        if (data.navigatepageNums[i] != data.pageNum) {
            navigatepageNumsStr +=
                "<li><a href='javascript:itemByPn(" + data.navigatepageNums[i] + ")'>" + data.navigatepageNums[i] + "</a></li>"

        }
    }
    if (data.hasNextPage) {
        hasNextPageStr +=
            "<li><a href='javascript:itemByPn(" + data.pageNum + 1 + ")' aria-label='Previous'><span aria-hidden='true'>&raquo;</span></a></li>"
    }
    Str = Str + hasPreviousPageStr + navigatepageNumsStr +
        "<li><a href='javascript:itemByPn(" + data.pages + ")'>末页</a>" +
        "</li>" +
        "</ul>" +
        "</div>"
    // alert(Str)
    $(".pager").append(Str)
}

// 将需要重新加载数据的元素清空
function clear() {
    $('.item tbody').empty()
    $(".pager").empty()
}

function del(id) {
    $.ajax({
        type: 'post',
        url: "http://127.0.0.1:8080/ssm_sun_mall_war/product/del?id=" + id,

        success: function (data) {
            console.log(data)
            // window.location.href = "http://127.0.0.1:8080/ssm_sun_mall_war/pages/showProduct"
            clear();
            listItem(data)
            pages(data)
        }
    })
}

function upd(id) {
    $.ajax({
        type: 'post',
        url: "http://127.0.0.1:8080/ssm_sun_mall_war/product/findOne?id=" + id,
        success: function (data) {
            console.log(data + "===============================" + id)
            $("#updId").val(data.id)
            $("#updName").val(data.name)
            $("#updSubtitle").val(data.subtitle)
            $("#updPrice").val(data.price)
            $("#updMainImg").val(data.mainImage)
            $("#updSubImages").val(data.subImage)
            $("#updDetail").val(data.detail)
        }
    })
}

function updSave() {
    $("#updSave").click(function () {
        let price = $("#updPrice").val()
        if (isNaN(price) || price == null || price == undefined || price ==""){
            alert("请输入合法的价格")
            return
        }

        $.ajax({
            type: "POST",   //提交的方法
            url: "http://127.0.0.1:8080/ssm_sun_mall_war/product/upd", //提交的地址
            data: $('#updForm').serialize(),// 序列化表单值
            async: false,
            error: function (request) {  //失败的话
                console.log(request)
                alert("Connection error" + request);
            },
            success: function (data) {  //成功
                alert("更新成功")
                window.location.href = "http://127.0.0.1:8080/ssm_sun_mall_war/pages/showProduct"
            }
        });
    });
}

//复选框的更改,如:选择,取消选择
function updateSelection(selectFlag) {
    //清空
    ids = '';
    //页面input的name属性
    var selectIds = $("[name=ids]");
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
    // alert(ids)
}

$(function () {
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
//批量删除事件
function deleteList() {
    //清空
    ids = '';
    //页面input的name属性
    var selectIds = $("[name=ids]");
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
            url: "http://127.0.0.1:8080/ssm_sun_mall_war/product/delList?ids=" + ids ,
            success: function (data) {//删除成功后，后台会返回一个"ok";
                if (data.errorMsg != "删除错误") {
                    alert("删除成功");//返回ok后弹出一个对话框。
                    window.location.href = "http://127.0.0.1:8080/ssm_sun_mall_war/pages/showProduct"
                } else {
                    alert("删除失败");
                }
            }
        });
    }
}

function likeQuery() {
    let name = $("[name=name]").val()
    if (name.length<=0 || name == null || name == '' || name == undefined){
        alert("请输入要搜索的名字")
        return
    }else {
        $.ajax({
            url:"http://127.0.0.1:8080/ssm_sun_mall_war/product/likeQuery?name="+name,
            success(data){
                clear();
                listItem(data)
                pages(data)
            }
        })
    }
}

function addForm() {
    let categoryId = $("#categoryId").val()
    let name = $("#addname").val()
    let subtitle = $("#subtitle").val()
    let mainImage = $("#mainImage").val()
    let subImages = $("#subImages").val()
    let detail = $("#detail").val()
    let price = $("#price").val()

    if (judge(categoryId) || categoryId == "一请选择一"){
        alert("请选择商品分类")
        return false;
    }
    if (judge(name)){
        alert("请输入商品标题")
        return false;
    }
    if (judge(subtitle)){
        alert("请输入商品副标题")
        return false;
    }
    if (judge(mainImage)){
        alert("请上传商品图片")
        return false;
    }
    if (judge(subImages)){
        alert("请上传商品图片")
        return false;
    }
    if (judge(detail)){
        alert("请选择商品图片")
        return false;
    }
    if (judge(price) || isNaN(price)){
        alert("请输入合法的商品价格")
        return false;
    }
    return true;

}
function judge(arg) {
    if (arg == null || arg == undefined || arg.length == 0 || arg == ""){
        return true
    }else {
        return false
    }
}