<%--
  Created by IntelliJ IDEA.
  User: 17749
  Date: 2020/6/15
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Signin</title>

    <link href="http://127.0.0.1:8080/ssm_sun_mall_war/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://127.0.0.1:8080/ssm_sun_mall_war/css/signin.css" rel="stylesheet">


    <script src="http://127.0.0.1:8080/ssm_sun_mall_war/js/jquery-3.2.1.min.js"></script>
    <script src="http://127.0.0.1:8080/ssm_sun_mall_war/js/bootstrap.min.js"></script>
    <script src="http://127.0.0.1:8080/ssm_sun_mall_war/js/login.js" type="text/javascript"></script>
    <script src="http://127.0.0.1:8080/ssm_sun_mall_war/js/jquery.cookie.js" type="text/javascript" ></script>

</head>

<body>
<h2>${msg}</h2>
<div class="container">
    <%--    登陆框--%>
    <div class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputUsername" class="sr-only">Email address</label>
        <input type="text" id="inputUsername" class="form-control" name="username" placeholder="Username" required
               autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" name="password" placeholder="Password" required>
        <button class="btn  btn-primary pull-left" type="button" id="login">Sign in</button>
        <button type="button" class="btn btn-danger pull-right" name="register" id="register"
                data-toggle="modal" data-target="#myModal">register
        </button>
    </div>
    <%--    注册框--%>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">注册新用户</h4>
                </div>
                <!-- form表单提交 -->
                <form class="form-horizontal" role="form"
                      action="#" method="" id="ajaxForm">
                    <div class="modal-body">
                        <%--                        账号用于登陆--%>
                        <div class="form-group">
                            <label for="username" class="col-sm-3 control-label">账号:</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="username" name="username"
                                       placeholder="请输入用账号" required autofocus>
                            </div>
                        </div>
                        <%--                            用户名用于显示用户的昵称--%>
                        <div class="form-group">
                            <label for="nikename" class="col-sm-3 control-label">用户名:</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="nikename" name="nikename"
                                       placeholder="请输入用户名" required autofocus>
                            </div>
                        </div>
                        <%--                            密码用于登陆验证--%>
                        <div class="form-group">
                            <label for="password" class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;码:</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="请输入密码" required autofocus>
                            </div>
                        </div>
                        <%--                            用户邮箱--%>
                        <div class="form-group">
                            <label for="email" class="col-sm-3 control-label">邮&nbsp;&nbsp;&nbsp;箱:</label>
                            <div class="col-sm-6">
                                <input type="email" class="form-control" id="email" name="email"
                                       placeholder="请输入邮箱" required autofocus>
                            </div>
                        </div>
                        <%--                            用户电话--%>
                        <div class="form-group">
                            <label for="phone" class="col-sm-3 control-label">电&nbsp;&nbsp;&nbsp;话:</label>
                            <div class="col-sm-6">
                                <input type="phone" class="form-control" id="phone" name="phone"
                                       placeholder="请输入电话号" required autofocus>
                            </div>
                        </div>
                        <%--                        权限--%>
                        <div class="form-group">
                            <label for="role0" class="col-sm-3 control-label ">
                                <input type="radio" name="role" id="role0" class="radio-inline" value="0" checked>
                                管理员</label>
                            <label for="role1" class="col-sm-3 control-label">
                                <input type="radio" name="role" id="role1" class="radio-inline" value="1">
                                用户</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="save">注册</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>