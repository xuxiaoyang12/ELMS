<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mxia
  Date: 2017/2/18
  Time: 16:04
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
<div class="wrapper">

    <%@include file="../../navber/header.jsp"%>

    <!-- =============================================== -->

    <!-- Left side column. contains the sidebar -->
    <%@include file="../../navber/aside.jsp"%>

    <!-- =============================================== -->

    <h3 style="text-align:center" class="visible-print-block">领先科技管理有限公司租赁合同清单</h3>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">新增租赁详情页</h3>
                </div>
                <div class="box-body">
                    <div class="box">
                        <div class="box-header with-border">
                            <h2 class="box-title">设备详情</h2>
                            <div class="box-tools pull-right hidden-print">
                                <button id="print" class="btn btn-default btn-sm">
                                    <i class="fa fa-print"></i>打印</button>
                            </div>
                        </div>

                        <div class="box-body">
                            <form role="form" >
                                <div class="box-body" class="form-group">

                                    <!--设备 -->
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div >
                                                <label >设备名称：&nbsp</label>
                                                ${deviceRentDetail.deviceName}
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div >
                                                <label >单 &nbsp;&nbsp位：&nbsp</label>
                                                ${deviceRentDetail.deviceUnit}
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div>
                                                <label >租赁单价：&nbsp</label>
                                                ${deviceRentDetail.devicePrice}
                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div >
                                                <label>数 &nbsp;&nbsp量：&nbsp</label>
                                                ${deviceRentDetail.num}
                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label>总金额：&nbsp</label>
                                                ${deviceRentDetail.totalCost}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <!-- /.box-body -->

                    </div>
                    <!-- /.box -->
                    <div class="box">
                        <div class="box-header with-border">
                            <h2 class="box-title">合同扫描件</h2>
                                <div class="box-tools pull-right hidden-print">
                                    <a href="/device/rent/doc/zip?id=${deviceRent.id}" class="btn btn-default btn-sm" >
                                        <i class="fa fa-download">打包下载</i>
                                    </a>
                                </div>
                        </div>
                        <div class="box-body">
                            <ul id="fileList">
                                <c:forEach items="${deviceRentDocList}" var="doc">
                                    <li><a href="/device/rent/doc?id=${doc.id}">${doc.sourceFileName}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
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

<%@include file="../../navber/script.jsp"%>
<script>
    $(function () {
        $("#print").click(function () {
            window.print();
        })

    })
</script>

</body>
</html>

