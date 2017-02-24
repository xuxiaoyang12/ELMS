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
    <link rel="stylesheet" href="/static/plugins/datatables/jquery.dataTables.css">
    <link rel="stylesheet" href="/static/plugins/datatables/extensions/FixedHeader/js/dataTables.fixedHeader.js">
    <link rel="stylesheet" href="/static/css/bootstrap-datepicker.min.css">

</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../navber/header.jsp"%>

    <%@include file="../navber/aside.jsp"%>

    <!-- =============================================== -->

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">
            <!-- Default box -->
            <div class="box">
                <div class="box-header">

                    <h3>财务流水</h3>
                    <input type="text" id="dateInput">
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>财务流水号</th>
                            <th>财务流水名称</th>
                            <th>业务模块</th>
                            <th>业务流水号</th>
                            <th>金额</th>
                            <th>状态</th>
                            <th>创建时间</th>
                            <th>备注</th>
                            <th>##</th>
                        </tr>
                        </thead>

                       <%-- <c:forEach items="${financeList}" var="finance">
                            <tr>
                                <td>${finance.serialNumber}</td>
                                <td>${finance.name}</td>
                                <td>${finance.module}</td>
                                <td>${finance.deviceSerialNumber}</td>
                                <td>${finance.money}</td>
                                <td>${finance.state}</td>
                                <td>${finance.confirmDate}</td>
                                <td>${finance.remark}</td>
                                <td><a href="#" rel="${finance.id}">确认</a></td>
                            </tr>
                        </c:forEach>--%>
                    </table>
                </div>


            </div>
            <div class="box">
                <div class="box-body ">

                    <div style = "width:600px;height:300px" id="chart"></div>
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
<script src="/static/js/echarts.min.js"></script>
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/extensions/FixedHeader/js/dataTables.fixedHeader.js"></script>
<script src="/static/plugins/monment.js"></script>
<script src="/static/js/bootstrap-datepicker.min.js"></script>
<script src="/static/js/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>

    $(function () {
        $("#dateInput").val(moment().format("YYYY-MM-DD"));

        var table = $(".table").DataTable({
            "lengthChange": false,
            /*"lengthMenu": [10,50,100],*/
            "pageLength": 25,
            "serverSide": true,
            "ajax":{
                "url":"/statement/day/list",
                "type":"get",
                "data":function(obj){
                    obj.day = $("#dateInput").val()
                }
            },

            "columns":[
                {"data":"id","name":"id"},
                {"data":"serialNumber"},
                {"data":"name"},
                {"data":"module"},
                {"data":function (row) {
                    if(row.serialNumber) {
                        return "<a href='/device/rent/" + row.deviceSerialNumber + "'>" + row.deviceSerialNumber + "</a>";
                    } else {
                        return "";
                    }


                }},
                {"data":"money"},
                {"data":"state"},
                {"data":"confirmDate"},
                {"data":"remark"},
                {"data":function (row) {
                    if(row.state == '未确认'){
                        return "<a href='/device/rent/" + row + "'>确认</a>";
                    }

                }},
            ],
            "searching":false,//不使用自带的搜索
            "order":[[0,'desc']],//默认排序方式,
            "ordering": false,
            "autoWidth": false,//自动适应宽度
            "columnDefs":[
                {targets:[0],visible: false}
            ],


            "language":{ //定义中文
                "search": "搜索:",
                "zeroRecords":    "没有匹配的数据",
                "lengthMenu":     "显示 _MENU_ 条数据",
                "info":           "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
                "infoFiltered":   "(从 _MAX_ 条数据中过滤得来)",
                "loadingRecords": "加载中...",
                "processing":     "处理中...",
                "paginate": {
                    "first":      "首页",
                    "last":       "末页",
                    "next":       "下一页",
                    "previous":   "上一页"
                }
            }
        });
        $("#dateInput").datepicker({
            //语言格式
            language: "zh-CN",
            //选择后自动关闭
            autoclose: true,//选中之后自动隐藏日期选择框
            //clearBtn: true,//清除按钮
            //todayBtn: true,//今日按钮
            //设置日期显示格式
            format: "yyyy-mm-dd"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
            //设置只能选择今天之后的日期  add()增加天数
            //startDate: moment().add(1,"days").format("YYYY-MM-DD")
        }).on("changeDate",function (e) {
            var today = e.format(0,'yyyy-mm-dd');
            //table.ajax.reload(false,null);
            table.ajax.reload();
            loadPie();
        });
    });


    //使用datatables插件


    //基于准备好的dom  初始化echart实例
    var myChart = echarts.init(document.getElementById("chart"));
    //制定图表的配置项和数据

    function loadPie() {
        //异步获取各个模块的金额总和
        $.get("/statement/day/daySum",{"day":$("#dateInput").val()}).done(function (result) {
            if(result.status == 'success'){
                var nameArray = [];
                for(var i = 0 ; i < result.data.length;i++){
                    var obj = result.data[i];

                    nameArray.push(obj.name)
                }
                myChart.setOption({
                    title : {
                        text:"领先科技日报",
                        x:'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"

                    },
                    legend:{
                        orient: 'vertical',
                        left: 'left',
                        data: nameArray
                    },
                    series: [{
                        name: '收入',
                        type: 'pie',
                        data: result.data
                    }],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }



                });
            }else{
                layer.msg(result.message)
            }


        }).error(function () {
            layer.msg("加载饼图异常")
        })
    }


</script>
</body>
</html>


