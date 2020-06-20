$(function () {

})
function item() {
    $.ajax({
        url: "http://127.0.0.1:8080/ssm_sun_mall_war/product/queryProduct",
        success: function (data) {
            console.log(data)
            listItem(data)//商品
        }
    })
}
function listItem(data) {

}
function f() {

}