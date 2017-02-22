<%--
  Created by IntelliJ IDEA.
  User: Mxia
  Date: 2017/2/3
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>新增设备</title>
    <!-- Tell tde browser to be responsive to screen widtd -->
    <meta content="widtd=device-widtd, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/css/font-awesome.min.css">
    <!-- tdeme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from tde css/skins
         folder instead of downloading all of tdem to reduce tde load. -->
    <link rel="stylesheet" href="/static/dist/css/skins/_all-skins.min.css">


</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../navber/header.jsp"%>
    <!-- =============================================== -->

    <!-- Left side column. contains tde sidebar -->
    <jsp:include page="../../navber/aside.jsp">
        <jsp:param name="menu" value="device_manage_add"/>
    </jsp:include>
    <%--<%@include file="../../navber/aside.jsp"%>--%>
    <!-- =============================================== -->

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>

                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 业务</a></li>
                <li><a href="#">设备管理</a></li>
                <li class="active">新增设备</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header witd-border">
                    <h3 class="box-title">新增设备</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
                            <i class="fa fa-minus"></i></button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                            <i class="fa fa-times"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <div class="box">

                        <div class="box-body">
                            <div class="col-md-5">
                            <form  method="post">

                                <div class="form-group">
                                    <label>设备名称</label>
                                    <input type="text" name="name" value="" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>总数量</label>
                                    <input type="text" name="total" value="" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>库存量</label>
                                    <input type="text" name="currentTotal" value="" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>单位</label>
                                    <input type="text" name="unit" value="" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>租金(/每单位/每天)</label>
                                    <input type="text" name="price" value="" class="form-control">
                                </div>
                                <div>
                                    <button class="btn btn-primary">添加设备</button>
                                </div>


                            </form>
                            </div>
                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer clearfix">
                            <ul class="pagination pagination-sm no-margin pull-right">
                                <li><a href="#">&laquo;</a></li>
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">&raquo;</a></li>
                            </ul>
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
        <strong>Copyright &copy; 2017 <a href="http://www.baidu.com">领先科技</a>.</strong> All rights
        reserved.
    </footer>


</div>
<!-- ./wrapper -->

<%@include file="../../navber/script.jsp"%>
</body>
</html>

