<%--
  Created by IntelliJ IDEA.
  User: Mxia
  Date: 2017/2/24
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>财务报表-月报</title>
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
    <link rel="stylesheet" href="/static/plugins/datatables/jquery.dataTables.css">


</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../navber/header.jsp"%>

    <!-- =============================================== -->

    <!-- Left side column. contains the sidebar -->
    <jsp:include page="../navber/aside.jsp">
        <jsp:param name="menu" value="statement_month"/>
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
                <li><a href="#"><i class="fa fa-dashboard"></i> 财务报表</a></li>
                <li class="active">月报</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">财务月报</h3>

                </div>
                <div class="box-body">
                    <div class="box">
                        <div class="box-body">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>日期</th>
                                    <th>当日收入</th>
                                    <th>当日支出</th>
                                    <th>备注</th>
                                    <th>操作</th>

                                </tr>
                                </thead>

                                <tr>
                                    <td>日期</td>
                                    <td>当日收入</td>
                                    <td>当日支出</td>
                                    <td>备注</td>
                                    <td>操作</td>

                                </tr>
                            </table>
                        </div>
                    </div>
                    <!-- /.box -->
                    <div style="font-size:18px;font-weight:100" >2016年10月：收入<span class="alert-success">100000.00</span>元，支出<span class="alert-error">50000.00</span>元，盈利<span class="alert-warning">50000.00</span>元</div>
                </div>
                <!-- /.box-body -->

            </div>
            <!-- /.box -->
            <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
            <div id="month" style="width:auto;height:800px"></div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 2.3.3
        </div>
        <strong>Copyright &copy; 2017 <a href="http://hngc.com">河南功成</a>.</strong> All rights
        reserved.
    </footer>


</div>
<!-- ./wrapper -->

<!-- jQuery 2.2.0 -->
<%@include file="../navber/script.jsp"%>
<!-- echarts.js -->
<script src="/static/js/echarts.js"></script>
<script src="/static/plugins/datatables/jquery.dataTables.js"></script>
<script src="/static/plugins/monment.js"></script>

<script>

    // 基于准备好的dom，初始化echarts实例
    var monthChart = echarts.init(document.getElementById('month'));

    var table = $(".table").DataTable({
        "lengthChange": false,
        /*"lengthMenu": [10,50,100],*/
        "pageLength": 25,
        "serverSide": true,
        "ajax":{
            "url":"/statement/mouth/list",
            "type":"get",
            "data":function (obj) {
                obj.mouth = moment().format("YYYY-MM")

            }
        },

        "columns":[
            {"data":"confirmDate"},
            {"data":"inMoney"},
            {"data":"outMoney"},
            {"data":"remark"},
            {"data":"operation"},
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



    // 使用刚指定的配置项和数据显示图表。
    monthChart.setOption(
        {
            title : {
                text: '10月收入支出统计图',
                subtext: ''
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['收入','支出']
            },
            toolbox: {
                show : true,
                feature : {
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : ['1','2','3','4','5','6','8','9','10','11','12','13','14','15','16','18','19','20','21','22','26','27']
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'收入',
                    type:'bar',
                    data:[2230, 4119, 7110, 2312, 2516, 7617, 1356, 1622, 326, 2000, 6400, 3030,2230, 4119, 7110, 2312, 2516, 7617, 1356, 1622, 326, 2000, ],
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                },
                {
                    name:'支出',
                    type:'bar',
                    data:[2600, 5900, 9000, 2640, 2870, 7017, 1756, 1822, 4870, 1880, 2600, 5900, 9000, 2640, 2870, 7017, 1756, 1822, 4870, 1880, 600, 230],
                    markPoint : {
                        data : [
                            {name : '年最高', value : 9000, xAxis: 2, yAxis: 9000},
                            {name : '年最低', value : 230, xAxis: 21, yAxis: 230}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name : '平均值'}
                        ]
                    }
                }
            ]
        });
</script>
</body>
</html>
