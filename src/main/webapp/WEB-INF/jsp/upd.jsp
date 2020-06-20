<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>修改页面</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>

    <script type="text/javascript" src="http://127.0.0.1:8080/ssm_sun_mall_war/js/upd.js"></script>
</head>

<!-- 添加模态框（Modal）插件 -->
<script type="text/javascript">
    $(function () {
        $('#myModal').modal({
            keyboard: false,
            backdrop: true
        })
    });

</script>
<body>
<!-- 添加修改商品的模态框-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <!-- 模态框的标题 -->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">修改商品</h4>
            </div>

            <!-- 模态框的主体-表单头部 -->
            <form class="form-horizontal" role="form"
                  action=""
                  method="" id="ajaxForm" enctype="multipart/form-data">

                <!-- 将id作为隐藏域提交这样就不会出现找不到修改的数据而报错问题 -->
                <input type="hidden" name="id" value="${product.id }"/>

                <!-- 主体-表单内容 -->
                <div class="modal-body">
                    <div class="form-group  form-group-lg">
                        <label for="name" class="col-sm-3 control-label">商品名称:</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control input-lg" id="name"
                                   name="name" value="${product.name }" required autofocus>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="price" class="col-sm-3 control-label">商品价格:</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control input-lg" id="price"
                                   name="price" value="${product.price }" required autofocus>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mainImage" class="col-sm-3 control-label">商品主图片:</label>
                        <div class="col-sm-5">
                            <img style="width: 80px; height: 70px"
                                 src="${pageContext.request.contextPath }/upload/${product.mainImage}">
                            <br/>
                            <input type="file" class="form-control input-lg" id="mainImage"
                                   name="multiple_mainImage" multiple="multiple">
                        </div>
                    </div>
                    <%--						<div class="form-group">--%>
                    <%--							<label for="lastname" class="col-sm-3 control-label">商品图片:</label>--%>
                    <%--							<div class="col-sm-5">--%>
                    <%--								<c:if test="${product.mainImage!=null }">--%>
                    <%--									<img style="width: 80px; height: 70px"--%>
                    <%--										src="${pageContext.request.contextPath }/upload/${product.mainImage}">--%>
                    <%--									<br />--%>
                    <%--									<input type="file" name="mainImage" />--%>
                    <%--								</c:if>--%>
                    <%--							</div>--%>
                    <%--						</div>--%>
                    <div class="form-group">
                        <label for="detail" class="col-sm-3 control-label">商品介绍:</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control input-lg" id="detail"
                                   name="detail" value="${product.detail}" required autofocus>
                        </div>
                    </div>
                </div>
                <!-- 模态框的尾部 -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-default">
                        <a href="http://127.0.0.1:8080/ssm_sun_mall_war/pages/showProduct">返回</a></button>
                    <button type="button" class="btn btn-primary" id="save">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
