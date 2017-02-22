<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mxia
  Date: 2017/2/15
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>劳务外包新增</title>
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

    <jsp:include page="../../navber/aside.jsp">
        <jsp:param name="menu" value="device_workout_add"/>
    </jsp:include>
    
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>

                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 业务</a></li>
                <li><a href="#">劳务外包</a></li>
                <li class="active">新增流水</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">新增劳务外包流水</h3>

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
                            <form role="form" >
                                <div class="box-body" class="form-group">

                                    <!--公司 -->
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label>租赁公司：&nbsp</label>
                                                <input type="text"  class="" placeholder="">

                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div >
                                                <label>地 &nbsp;&nbsp址：&nbsp</label>
                                                <input type="text" class="" name="representative" placeholder="" >
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label>公司电话：&nbsp</label>
                                                <input type="text" class="" name="telephone" placeholder="" >
                                            </div>
                                        </div>



                                    </div>


                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label>法人代表：&nbsp</label>
                                                <input type="text"  class="" id="daynumber" placeholder="">

                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label>电 &nbsp;&nbsp话：&nbsp</label>
                                                <input type="text" class="" name="telephone" placeholder="" >
                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div>
                                                <label>身份证号：&nbsp</label>
                                                <input type="text" class="" placeholder="">
                                            </div>
                                        </div>
                                    </div>

                                    <!--金额 -->
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label>佣金金额：&nbsp</label>
                                                <input type="text" disabled="disabled" class="" id="sumMoney" placeholder=""value="10000.00">

                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label>预付款：&nbsp</label>
                                                <input type="text" class="" disabled="disabled" name="firstMoney" placeholder="" value="2000.00">
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div >
                                                <label>尾&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;款：&nbsp</label>
                                                <input type="text" class="" disabled="disabled" name="lastMoney" placeholder="" value="8000.00">
                                            </div>
                                        </div>
                                    </div>


                                    <!--工种 -->
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="" style="margin:20px;border:1px solid #bfbfbf;">
                                                <table class="table table-bordered" id="table" >
                                                    <tr>
                                                        <th><a href="javascript:;" id="addWorkerType">添加工种<span ><i class="fa fa-plus"/></span></a> </th>
                                                        <th>工种单位佣金</th>
                                                        <th>工种数量</th>
                                                        <th>小计</th>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <select style="height:25px;width:160px">
                                                                <option value="">请选择工种</option>
                                                                <c:forEach items="${workerTypeList}" var="workerType">
                                                                    <option value="${workerType.id}">${workerType.workName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </td>
                                                        <td><span class="workerPrice">0.00</span></td>
                                                        <td><input type="text" class ="workerTypeNum" style="width:50px"  placeholder="1"></td>
                                                        <td><span class ="totalCost">0.00</span></td>
                                                    </tr>


                                                </table>

                                            </div>
                                        </div>


                                    </div>
                                    <div> <br/></div>



                                    <div class="box" style="padding-left: 20px">
                                        <div class="box-header">
                                            <span class="title"><i class="fa fa-user"></i> 合同上传</span>
                                        </div>
                                        <form action="" class="form-horizontal">
                                            <hr>
                                            <p style="padding-left: 20px">注意事项</p>
                                            <ul>
                                                <li>上传合同扫描件要求清晰可见</li>
                                                <li>合同必须公司法人签字盖章</li>
                                            </ul>
                                            <div class="form-actions">
                                                <div id="picker">上传合同</div>

                                            </div>
                                        </form>

                                    </div>


                                    <div class="row">

                                        <div class="col-lg-3">

                                        </div>
                                        <div class="col-lg-3">
                                            <div class="box-footer">
                                                <button type="submit" class="btn btn-primary">提交</button>
                                                &nbsp;&nbsp;&nbsp;&nbsp;
                                                <button type="submit" class="btn btn-primary">重置</button>
                                            </div>
                                        </div>

                                    </div>

                                </div>
                            </form>
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

<%@include file="../../navber/script.jsp"%>
<!-- datepicker -->
<script src="/static/js/bootstrap-datepicker.min.js"></script>
<script src="/static/js/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="/static/js/uploader/webuploader.min.js"></script>
<script type="text/template" id="template">
    <tr>
        <td>
            <select style='height:25px;width:160px'>
                <option>请选择工种</option>
                <c:forEach items='${workerTypeList}' var='workerType'>
                    <option value='${workerType.id}'>${workerType.workName}</option>
                </c:forEach>
            </select>
        </td>
        <td><span class='workerPrice'>0.00</span></td>
        <td><input type='text' class ='workerTypeNum' style='width:50px'  placeholder='1'></td>
        <td><span class ='totalCost'>0.00</span></td>
    </tr>

</script>
<script src="/static/plugins/vue.js"></script>
<script>

    var app = new Vue({
       el:"#app",//声明哪个容器给vue.js管理
        data:{},
        methods:{

        }

    });

    $(function () {

        //日期组件
        $("#datepicker").datepicker({
            language: "zh-CN",
            autoclose: true,//选中之后自动隐藏日期选择框
            //clearBtn: true,//清除按钮
            //todayBtn: true,//今日按钮
            format: "yyyy-mm-dd"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
        });

        //文件上传
        var uploder = WebUploader.create({
            swf : "js/uploader/Uploader.swf",
            server: "#",
            pick: '#picker',
            auto : true,
            fileVal:'file'
            /*accept: {
             title: 'Images',
             extensions: 'gif,jpg,jpeg,bmp,png',
             mimeTypes: 'image/!*'
             }*/
        });

        //当选择工种时自动添加租金  数量默认为1 计算出总金额
       /* $("select").change(function () {

            alert("改变")
            //获取工种id
            var workerTypeId = $("select").val();
            alert(workerTypeId)
            //异步请求服务端获取 价格

            $.post("/device/workOut/add",{"id":workerTypeId},function(data){
                //赋值
                $("#workerPrice").text(data.workPrice);
                $("#totalCost").text(data.workPrice);

            })

        });*/
        //当工种数量失去焦点时自动计算金额
       /* $(".workerTypeNum").change(function () {
            var workerNum = $(".workerTypeNum").val();
            var totalCost = $(".totalCost").text();
            alert(workerNum)

            //$("#totalCost").text(parseInt(workerNum) * parseFloat(totalCost));

        });*/


        //添加工种点击事件

        $("#addWorkerType").click(function () {
            //自动添加html
            var html = "<tr> <td> <select style='height:25px;width:160px'> <option>请选择工种</option><c:forEach items='${workerTypeList}' var='workerType'> <option value='${workerType.id}'>${workerType.workName}</option></c:forEach> </select> </td> <td><span class='workerPrice'>0.00</span></td> <td><input type='text' class ='workerTypeNum' style='width:50px'  placeholder='1'></td> <td><span class ='totalCost'>0.00</span></td> </tr>"
            $("#table").append(html)

        })
        //添加事件委托
        $(document).delegate("select","change",function () {
            var workerTYids = $(this).val();//获取当前用户的id

            //通过id 得到工种的对象 并赋值
            $.post("/device/workOut/add",{"id":workerTYids},function(data){
                //赋值
                $(".workerPrice").text(data.workPrice);
                $(".totalCost").text(data.workPrice);


            })


        })
        $(document).delegate(".workerTypeNum","change",function () {
            var workerNum = $(".workerTypeNum").val();
            var totalCost = $(".totalCost").text();
            var workerNumm = document.getElementsByClassName(".workerTypeNum")
            alert(workerNumm)
            console.log(workerNumm)
        })

    });

</script>
</body>
</html>

