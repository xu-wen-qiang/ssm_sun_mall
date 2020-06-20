<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <title>商品后台管理系统</title>

    <link rel="stylesheet" href="http://127.0.0.1:8080/ssm_sun_mall_war/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://127.0.0.1:8080/ssm_sun_mall_war/css/bootstrap-datetimepicker.min.css"/>

    <script type="text/javascript" src="http://127.0.0.1:8080/ssm_sun_mall_war/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="http://127.0.0.1:8080/ssm_sun_mall_war/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://127.0.0.1:8080/ssm_sun_mall_war/js/jquery.form.min.js"></script>
    <script type="text/javascript" src="http://127.0.0.1:8080/ssm_sun_mall_war/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="http://127.0.0.1:8080/ssm_sun_mall_war/js/showProduct.js"></script>
    <script type="text/javascript">
        alert("javascript")
        $("#nikename").append($.cookie("nikename"))
    </script>
    <style>
        th, td {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-inverse" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#" style="height:92px;">
                    <img src="#" height="100%"/>
                </a>
            </div>
            <div class="collapse navbar-collapse" id="example-navbar-collapse">
                <ul class="nav navbar-nav navbar" style="margin:1% 0 1% 34%;">
                    <li class="active">
                        <a class="icon-bar" href="#" style="background-color:#087b71">
                            <font style="font-size:31px;font-weight:bold;font-style:italic;">欢迎来到商品后台管理系统</font></a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right" style="margin:1% 0 1% 0%;">
                    <li><h4 style="color:red;">
                        欢迎您:&nbsp;&nbsp;<span class="glyphicon glyphicon-user"></span>
                        <strong id="nikename"></strong><small>
                        <a href="http://127.0.0.1:8080/ssm_sun_mall_war/user/logout">注销</a></small>
                    </h4></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar category">
                    <li class="active"><a href="http://127.0.0.1:8080/ssm_sun_mall_war/pages/showProduct">商品分类 </a></li>
                </ul>
            </div>
            <!--左边菜单栏-->
            <div class="col-sm-10">

                <div class="panel panel-default">
                    <div class="panel-heading">搜索</div>
                    <div class="panel-body">
                        <div role="form" class="form-inline">
                            <div class="form-group">
                                <label for="name">名称</label>
                                <input type="text" class="form-control" id="name" placeholder="请输入名称">
                            </div>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <div class="form-group">
                                <button type="submit" class="btn btn-default">开始搜索</button>
                            </div>
                        </div>
                        <!-- 按钮-->
                        <div class="row">
                            <div class="col-md-6 col-md-offset-10">
                                <button type="button" class="btn btn-primary btn-lg"
                                        data-toggle="modal" data-target="#myModal">
                                    <span class="glyphicon glyphicon-plus"></span>添加商品
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 列表展示 -->
                <div class="table-responsive">
                    <form action="http://127.0.0.1:8080/ssm_sun_mall_war/product/addCar" method="post">
                        <table class="table table-striped item">
                            <thead>
                            <tr>
                                <th>商品名称</th>
                                <th>商品价格</th>
                                <th>商品主图片</th>
                                <th>商品副图片</th>
                                <th>商品介绍</th>
                                <th colspan="4">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <!-- 分页 -->
        <div class="row pager">
            <!-- 分页信息 -->
        </div>
    </div>
    <!-- 添加商品的模态框-->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <!-- 模态框的标题 -->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">添加商品</h4>
                </div>
                <!-- 模态框的主体-表单头部 -->
                <form class="form-horizontal" role="form"
                      action="http://127.0.0.1:8080/ssm_sun_mall_war/product/addProduct"
                      onsubmit='function x() {
                    window.location.href="window.location.href = \"http://127.0.0.1:8080/ssm_sun_mall_war/pages/showProduct\""
                  }'
                      method="post" id="form" enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="categoryId" class="col-sm-3 control-label"> 商品分类</label>
                            <div class="col-sm-5">
                                <select class="form-control input-lg" id="categoryId"
                                        name="categoryId">
                                    <option value="">一请选择一</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group  form-group-lg">
                            <label for="name" class="col-sm-3 control-label">商品名称:</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control input-lg" id="name"
                                       name="name" placeholder="请输入商品名字" required autofocus>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="subtitle" class="col-sm-3 control-label">商品副标题:</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control input-lg" id="subtitle"
                                       name="subtitle" placeholder="请输入商品介绍" required autofocus>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="mainImage" class="col-sm-3 control-label">商品主图片:</label>
                            <div class="col-sm-5">
                                <input type="file" class="form-control input-lg" id="mainImage"
                                       name="multiple_mainImage" multiple="multiple">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="subImages" class="col-sm-3 control-label">商品副图片:</label>
                            <div class="col-sm-5">
                                <input type="file" class="form-control input-lg" id="subImages"
                                       name="multiple_subImages" multiple="multiple">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="detail" class="col-sm-3 control-label">商品介绍:</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control input-lg" id="detail"
                                       name="detail" placeholder="请输入商品介绍" required autofocus>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="price" class="col-sm-3 control-label">商品价格:</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control input-lg" id="price"
                                       name="price" placeholder="请输入商品价格" required autofocus>
                            </div>
                        </div>
                    </div>
                    <!-- 模态框的尾部 -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary" id="save">保存</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- 底部页脚部分 -->
    <div class="footer">
        <p class="text-center">
            2020 &copy; <span color="red">☀</span>.
        </p>
    </div>


</div>
</body>
</html>