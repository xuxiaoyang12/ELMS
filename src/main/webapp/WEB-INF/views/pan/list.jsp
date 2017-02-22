<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mxia
  Date: 2017/2/21
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Mxia
  Date: 2017/2/21
  Time: 8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>公司网盘</title>
    <!-- Tell tde browser to be responsive to screen widtd -->
    <meta content="widtd=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/css/font-awesome.min.css">
    <!-- tdeme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from tde css/skins
         folder instead of downloading all of tdem to reduce tde load. -->
    <link rel="stylesheet" href="/static/dist/css/skins/_all-skins.min.css">

    <!-- 文件上传 -->
    <link rel="stylesheet" href="/static/js/uploader/webuploader.css">
    <link rel="stylesheet" href="/static/css/style.css">

</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../navber/header.jsp"%>

    <jsp:include page="../navber/aside.jsp">
        <jsp:param name="menu" value="pan"/>
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
                <li><a href="#"><i class="fa fa-dashboard"></i> 公司网盘</a></li>
                <li><a href="#">浏览网盘</a></li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header witd-border">
                    <h3 class="box-title">文件列表</h3>
                </div>
                <div class="box-body">
                    <div class="box">
                        <div class="pull-left">
                            <i class="fa fa-arrow-circle-left "style="font-size: large ; margin-top: 10px;" >
                            </i>
                            <i class="fa fa-arrow-circle-right "style="font-size: large ; margin-top: 10px;" >
                            </i>
                        </div>

                        <div class="form-actions" style="float:right">

                            <button id="newFolder" class="btn btn-primary newFile" style="float:right">新建文件夹</button>&nbsp;&nbsp;
                            <div id="picker" style="float:right">上传文件</div>&nbsp;&nbsp;

                        </div>
                        <div class="box-body">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>文件</th>
                                        <th>文件名</th>
                                        <th>文件大小</th>
                                        <th>创建时间</th>
                                        <th>创建者</th>
                                        <th>#</th>

                                    </tr>
                                </thead>

                                <tbody>
                                <c:if test="${ empty diskList }">
                                    <tr>
                                        <td colspan="6" >暂无资源</td>
                                    </tr>
                                </c:if>


                                        <c:forEach items="${diskList}" var="disk">
                                        <tr>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${disk.type == 'file'}">
                                                        <i class="fa fa-file"></i>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <i class="fa fa-folder"></i>
                                                    </c:otherwise>
                                                </c:choose>

                                            </td>
                                            <td>
                                            <c:choose>
                                                <c:when test="${disk.type == 'file'}">
                                                   <a href="/pan/download?path=${disk.id}">${disk.sourceName}</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="/pan/list?path=${disk.id}">${disk.sourceName}</a>
                                                </c:otherwise>
                                            </c:choose>
                                            </td>
                                            <td>${disk.size}</td>
                                            <td>${disk.createTime}</td>
                                            <td>${disk.createUser}</td>
                                            <td><a href="javascript:;" class="remove" rel="${disk.id}"><i class="fa fa-trash-o"></i></a></td>
                                        </tr>
                                        </c:forEach>


                                </tbody>


                            </table>
                        </div>
                        <!-- /.box-body -->

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
<%@include file="../navber/script.jsp"%>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {

        //文件上传
        var uploder = WebUploader.create({
            swf : "js/uploader/Uploader.swf",
            server: "/pan/upload",
            pick: '#picker',
            auto : true,
            fileVal:'file',
            formData:{"fid":${fid}}
        });
        uploder.on("uploadSuccess",function (file,resp) {

            if(resp.status = 'success'){
                window.history.go(0);
                layer.msg("上传成功！");
            }else{
                layer.msg(resp.message);
            }
        });
        uploder.on("uploadError",function () {
            layer.msg("服务器错误，请稍后重试！")
        });

        $("#arrow").click(function () {
            window.history.go(-1);
        });

        $("#newFolder").click(function(){
            var fid = ${fid};
            layer.prompt({title:"请输入文件夹名称"},function(text,index){
                layer.close(index);
                $.post("/pan/folder/new",{"fid":fid,"sourceName":text}).done(function(resp){
                    if(resp.status == 'success') {
                        window.history.go(0);
                    } else {
                        layer.msg(resp.message);
                    }
                }).error(function(){
                    layer.msg("服务器异常");
                });
            });
        });
        $(".remove").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除吗？",function (index) {
                layer.close(index);
                $.get("/pan/del/"+id).done(function (resp) {
                    if(resp.status == 'success'){
                        layer.msg("删除成功");
                        window.history.go(0);
                    }else{
                        layer.msg(resp.message);
                    }
                }).error(function () {
                    layer.msg("服务器忙请稍后重试")
                })
            })
        })
    });
</script>
</body>
</html>


