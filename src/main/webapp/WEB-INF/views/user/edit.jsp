<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mxia
  Date: 2017/2/25
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>设备租赁新增</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="/static/dist/css/skins/_all-skins.min.css">

    <link rel="stylesheet" href="/static/css/bootstrap-datepicker.min.css">
    <!-- 文件上传 -->
    <link rel="stylesheet" href="/static/js/uploader/webuploader.css">
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div id="app" class="wrapper">

    <%@include file="../navber/header.jsp"%>

    <!-- =============================================== -->

    <!-- Left side column. contains the sidebar -->
    <%@include file="../navber/aside.jsp"%>
    <jsp:include page="../navber/aside.jsp">
        <jsp:param name="menu" value=""/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>

                <small></small>
            </h1>

        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">编辑账户</h3>
                </div>
                <div class="box-body">
                    <div class="box">
                        <div style="margin-top: 20px;"></div>
                        <div class="col-md-5">

                            <form action="/user/edit" method="post">
                                <input type="hidden" name="id" value="${user.id}">
                                <div class="form-group">
                                    <label>账户名称</label>
                                    <input type="text" name="name" value="${user.name}" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>密码(默认000000)</label>
                                    <input type="password" name="password" value="${user.password}"  class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>手机号码</label>
                                    <input type="text" name="mobile"  value="${user.mobile}" class="form-control">
                                </div>

                                <div class="form-group">
                                    <label>角色</label><br/>
                                    <c:forEach items="${roleList}" var="role">
                                        <c:set var="flag" value="false" scope="page"/>
                                        <c:forEach items="${user.roleList}" var="userRole">
                                            <c:if test="${role.id == userRole.id}">
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" checked name="roleIds" value="${role.id}">${role.roleName}
                                                </label>
                                                <c:set var="flag" value="true"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${not flag}">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" name="roleIds" value="${role.id}">${role.roleName}
                                            </label>
                                        </c:if>
                                    </c:forEach>

                                </div>
                                <div class="form-group">
                                    <button id="submitBtn" class="btn btn-primary">编辑</button>
                                    <button type="button" id="reBackBtn" class="btn btn-default">  取消  </button>
                                </div>



                            </form>

                        </div>

                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.box-body -->

            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 2.3.3
        </div>
        <strong>Copyright &copy; 2017 <a href="http://hngc.com">领先科技</a>.</strong> All rights
        reserved.
    </footer>


</div>
<!-- ./wrapper -->

<!-- jQuery 2.2.0 -->
<%@include file="../navber/script.jsp"%>
<!-- datepicker -->
<script>
    $(function () {
        $("#reBackBtn").click(function () {
            window.history.go(-1);
        })


    });
</script>
</body>

</html>
