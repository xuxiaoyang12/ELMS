<%--
  Created by IntelliJ IDEA.
  User: Mxia
  Date: 2017/2/25
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <ol class="breadcrumb">
                <li><a href="/user/add"><i class="fa fa-dashboard"></i>账户管理</a></li>
                <li><a href="/user/list">账户列表</a></li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">账户列表</h3>
                </div>
                <div class="box-body">
                    <div class="box">
                        <c:if test="${not empty message}">
                            <div class="alert alert-success">
                                ${message}
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                            </div>
                        </c:if>
                        <table class="table" >
                            <thead>
                                <tr>
                                    <th>账户</th>
                                    <th>部门</th>
                                    <th>手机号</th>
                                    <th>#</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${userList}" var="user">
                                <tr>
                                    <td>${user.name}</td>
                                    <td>${user.roleNames}</td>
                                    <td>${user.mobile}</td>
                                    <td>
                                        <a href="/user/edit?id=${user.id}">编辑</a> /
                                        <a href="/user/del?id=${user.id}">删除</a>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>



                        </table>

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
</body>
</html>
