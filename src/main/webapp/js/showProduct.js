// 页面初始化获取数据
$(function () {
    let nikename = $.cookie("nikename")
    alert(nikename)
    // $("#nikename").append(nikename+"1")
    category()
    item()
})

// 添加模态框（Modal）插件
function myModal() {
    $("#myModal").modal({
        keyboard: false,
        backdrop: true
    });
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
    let uploadUrl = `http://127.0.0.1:8080/ssm_sun_mall_war/upload/`;
    for (var i in data.list) {
        htmlStr += "<tr>" +
            "<td>" + data.list[i].name + "</td><td>" + data.list[i].price + "</td>" +
            "<td><img title='" + data.list[i].name + "'style='width: 60px; height: 60px' src=" + uploadUrl + data.list[i].name + "1.jpg></td>" +
            "<td><img title='" + data.list[i].subtitle + "'style='width: 60px; height: 60px' src=" + uploadUrl + data.list[i].name + "2.jpg></td>" +
            "<td>" + data.list[i].detail + "</td>" +
            "<td><a href=\"javascript:del(" + data.list[i].id + ")\")>" +
            "<button type=\"button\" class=\"btn btn-success btn-lg\" onclick=\"return confirm('确定要删除信息吗？') \">" +
            "<span class=\"glyphicon glyphicon-trash\"></span>删除</button></a></td>" +
            "<td>" +
            "<a href=\"http://127.0.0.1:8080/ssm_sun_mall_war/product/findOne?id=" + data.list[i].id + "\">" +
            "<button type=\"button\" class=\"btn btn-success btn-lg\">\n" +
            "<span class=\"glyphicon glyphicon-edit\"></span> 修改 </button></a></td>" +
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