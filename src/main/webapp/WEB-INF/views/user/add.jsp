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
                <li><a href="#"><i class="fa fa-dashboard"></i>账户管理</a></li>
                <li><a href="#">新增账户</a></li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">新增账户</h3>
                </div>
                <div class="box-body">
                    <div class="box">
                        <div style="margin-top: 20px;"></div>
                        <div class="col-md-5">

                            <form action="/user/add" method="post">
                                <div class="form-group">
                                    <label>账户名称</label>
                                    <input type="text" name="name" value="" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>密码(默认000000)</label>
                                    <input type="password" name="password" value="000000"  class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>手机号码</label>
                                    <input type="text" name="mobile" class="form-control">
                                </div>

                                <div class="form-group">
                                    <label>角色</label><br/>
                                    <c:forEach items="${roleList}" var="role">
                                        <label class = "checkbox-inline">
                                            <input type="checkbox" name="roleIds" value="${role.id}">${role.roleName}
                                        </label>
                                    </c:forEach>
                                </div>
                                <div class="form-group">
                                    <button class="btn btn-primary">添加</button>
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
<script src="/static/js/bootstrap-datepicker.min.js"></script>
<script src="/static/js/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="/static/js/uploader/webuploader.min.js"></script>
<script src="/static/plugins/vue.js"></script>
<script src="/static/plugins/monment.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script>

    var fileArray = [];
    var currentDeviceName;

    $(function () {

        //生成归还日期选择框
        $("#datepicker").datepicker({
            //语言格式
            language: "zh-CN",
            //选择后自动关闭
            autoclose: true,//选中之后自动隐藏日期选择框
            //clearBtn: true,//清除按钮
            //todayBtn: true,//今日按钮
            //设置日期显示格式
            format: "yyyy-mm-dd",//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
            //设置只能选择今天之后的日期  add()增加天数
            startDate: moment().add(1,"days").format("YYYY-MM-DD")
        });
        //当日期选择失去焦点后异步获取天数
        $("#datepicker").change(function () {
            var now = moment().format("X");//获取时间戳
            var returnBackTime = $("#datepicker").val();
            var reBack = moment(returnBackTime).format("X");
            var days = parseInt((reBack - now )*1000/(24 * 60 * 60 * 1000) + 1);
            $("#days").val(days);
        });

        //异步实现 当选择设备是 同时自动添加单位和单价
        $("select").change(function () {
            //获取被选中后的设备id
            var id = document.getElementById("select").value;
            //异步post请求
            $.post("/device/manage/findByName",{"id":id},function (data) {
                //分别给unit和 price 赋值
                $("#unit").val(data.unit);
                $("#daynumber").val(data.price);
                currentDeviceName = data.name;


            })
        });

        //当数量文本框失去焦点时 自动计算 租金 预付款 和尾款金额
        $("#num").change(function(){
            //获取租赁数量
            var num = parseFloat($(this).val());
            //获取租赁单价
            var unit = parseFloat($("#daynumber").val());
            //获取租赁天数
            var days = parseInt($("#days").val());
            //计算出租金 预付款 尾款
            var sumMoney= num * unit * days;
            var firstMoney = sumMoney * 0.3;
            var lastMoney = sumMoney * 0.7;
            $("#sumMoney").val(sumMoney);
            $("#firstMoney").val(firstMoney);
            $("#lastMoney").val(lastMoney);
        });

//        //提交表单
//        $("#submitBtn").click(function () {
//            $("#submitForm").submit();
//        });
//
//        //重置
//        $("#resetBtn").click(function () {
//            window.history.go(0);
//        });

        //文件上传
        var uploder = WebUploader.create({
            swf : "js/uploader/Uploader.swf",
            server: "/file/upload",
            pick: '#picker',
            auto : true,
            fileVal:'file'
            /*accept: {
             title: 'Images',
             extensions: 'gif,jpg,jpeg,bmp,png',
             mimeTypes: 'image/!*'
             }*/
        });
        //上传成功
        //file 文件信息  resp 服务端返回的值
        uploder.on("uploadSuccess",function(file,resp){
            if(resp.status == "success") {
                //上传成功后自动添加上传列表
                var html = "<li>合同上传列表：</li><li>"+resp.data.sourceFileName+"</li>";
                $("#fileList").append(html);
                var json = {
                    sourceName : resp.data.sourceFileName,
                    newName : resp.data.newFileName
                };
                fileArray.push(json);


            }else{
                alert(data.message);
            }

        });
        //上传失败
        uploder.on("uploadError",function () {
            alert("服务器错误！");
        })
    });

    //提交合同表单 使用vue.js插件
    var app = new Vue({
        el:"#app", //声明那个容器给vue。js 管理
        data:{

        },//响应式 数据
        methods:{
            submit:function(){
                //把表单封装成json数组
                var json = {
                    deviceName : currentDeviceName,
                    deviceUnit : $("#unit").val(),
                    devicePrice : $("#daynumber").val(),
                    createTime : moment().format("YYYY-MM-DD"),
                    rebackTime : $("#datepicker").val(),
                    num : $("#num").val(),
                    days : $("#days").val(),
                    corportion : $("#corportion").val(),
                    company : $("#company").val(),
                    identityCard : $("#identityCard").val(),
                    address : $("#address").val(),
                    companyPhone : $("#companyPhone").val(),
                    phone : $("#phone").val(),
                    totalCost : $("#sumMoney").val(),
                    firstCost : $("#firstMoney").val(),
                    lastCost : $("#lastMoney").val(),
                    fileArray : fileArray

                };
                //异步请求保存数据
                $.ajax({
                    url : "/device/rent/add",
                    type : "post",
                    data :  JSON.stringify(json),
                    contentType : "application/json;charset=UTF-8",
                    beforeSend :function () {
                        $("#submitBtn").text("提交中...").attr("disabled","disabled");
                    },
                    success: function (data) {
                        if(data.status=="success"){
                            layer.confirm("上传合同成功",{
                                btn:['继续添加','打印合同']
                            },function () {
                                window.history.go(0);
                            },function () {
                                window.location.href="/device/rent/"+data.data
                            })
                        }else{
                            layer.msg(data.message);
                        }


                    },
                    error:function () {
                        alert("提交数据失败");
                    },
                    complete:function () {
                        $("#submitBtn").text("提交").removeAttr("disabled");
                    }
                })

            }
        }


    })



</script>
</body>
</html>
