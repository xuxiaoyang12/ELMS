<%--
  Created by IntelliJ IDEA.
  User: Mxia
  Date: 2017/2/22
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>新增设备租赁合同</title>
    <%@include file="../../navber/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="/static/plugins/select2/select2.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper" id="app">

    <%@include file="../../navber/header.jsp"%>
    <jsp:include page="../../navber/aside.jsp">
        <jsp:param name="menu" value="device_workout_add"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">新增租赁合同</h3>

                    <div class="box-tools pull-right">
                        <a href="/device/rent" class="btn btn-default btn-sm"><i class="fa fa-reply"></i></a>
                    </div>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>公司名称</label>
                                <input type="text" class="form-control" id="companyName" tabindex="1">
                            </div>
                            <div class="form-group">
                                <label>联系电话</label>
                                <input type="text" class="form-control" id="tel" tabindex="4">
                            </div>
                            <div class="form-group">
                                <label>租赁日期</label>
                                <input type="text" class="form-control" id="rentDate" readonly>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>法人代表</label>
                                <input type="text" class="form-control" id="linkMan" tabindex="2">
                            </div>
                            <div class="form-group">
                                <label>地址</label>
                                <input type="text" class="form-control" id="address" tabindex="5">
                            </div>
                            <div class="form-group">
                                <label>归还日期</label>
                                <input type="text" class="form-control" id="backDate" tabindex="7">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>身份证号</label>
                                <input type="text" class="form-control" id="cardNum" tabindex="3">
                            </div>
                            <div class="form-group">
                                <label>传真</label>
                                <input type="text" class="form-control" id="fax" tabindex="6">
                            </div>
                            <div class="form-group">
                                <label>总天数</label>
                                <input type="text" class="form-control" id="totalDays" readonly>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">工种列表</h3>
                    <div class="box-tools pull-right">
                        <button class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>工种</th>
                            <th>雇佣金额(天/元)</th>
                            <th>雇佣数量</th>
                            <th>总价</th>
                            <th>#</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-if="workerArray.length == 0">
                            <td colspan="5">暂无数据</td>
                        </tr>
                        <tr v-for="worker in workerArray">
                            <td>{{worker.name}}</td>
                            <td>{{worker.price}}</td>
                            <td>{{worker.num}}</td>
                            <td>{{worker.total}}</td>
                            <td><a href="javascript:;" @click="remove(device)"><i class="fa fa-trash text-danger"></i></a></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="box-footer" style="text-align: right">
                    总租赁费 {{total}} 元 预付款 {{preCost}} 元 尾款 {{lastCost}}  元
                </div>
            </div>

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">合同扫描件</h3>
                </div>
                <div class="box-body">
                    <div id="picker">选择文件</div>
                    <p>注意：上传合同扫描件要求清晰可见 合同必须公司法人签字盖章</p>
                    <ul id="fileList">
                    </ul>
                    <button class="btn btn-primary pull-right" type="button" @click="">保存合同</button>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <div class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">选择设备</h4>
                </div>
                <div class="modal-body">
                    <form action="">
                        <div class="form-group">
                            <input type="hidden" id="workerName">
                            <label>工种列表</label>
                            <select id="selectWorker" style="width: 300px;" class="form-control">
                                <option value="">选择工种</option>
                                <c:forEach items="${workerTypeList}" var="worker">
                                    <option value="${worker.id}">${worker.workName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>工种数量</label>
                            <input type="text" class="form-control" id="currNum" readonly>
                        </div>
                        <div class="form-group">
                            <label>劳务费(天/元)</label>
                            <input type="text" class="form-control" id="workerPrice" readonly>
                        </div>
                        <div class="form-group">
                            <label>雇佣数量</label>
                            <input type="text" class="form-control" id="rentNum">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" v-on:click="addWorker">加入列表</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

</div>

<%@include file="../../navber/script.jsp"%>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="/static/plugins/select2/select2.full.min.js"></script>
<script src="/static/plugins/vue.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>

    $(function () {

        //当工种改变时 显示其生于数量和雇佣租金
        $("#selectWorker").change(function () {
            //得到被选择的工种id
            var workerId = $(this).val();
            //通过id查询工种信息
            $.get("/device/workOut/worker.json",{"id" : workerId}).done(function (resp) {
                if(resp.status = 'success'){
                    $("#workerName").val(resp.data.workName);
                    $("#currNum").val(resp.data.workNum);
                    $("#workerPrice").val(resp.data.workPrice);
                }else{
                 layer.msg(resp.message);
                }
            }).error(function () {
                layer.msg("服务器错误");
            })
        })



    });
    var app = new Vue({
        el : "#app",
        data : {
            workerArray:[]
        },
        methods : {
            //添加工种方法
            addWorker:function(){
                 var id = $("#selectWorker").val();
                //判断已选择工种列表有没有此工种
                var flag = false;
                for(var i ; i < this.$data.workerArray.length;i++) {
                    var item = this.$data.workerArray[i];

                    alert(item.id)
                    alert(id)
                    if (item.id == id) {
                        //当工种列表中已经有词工种是 直接在数量和租金上相加即可
                        this.$data.workerArray[i].num = parseInt(this.$data.workerArray[i].num) + parseInt($("#rentNum").val());
                        this.$data.workerArray[i].total = parseFloat(this.$data.workerArray[i].num) * parseInt($("#workerPrice").val());
                        flag = true;
                        break;
                    }
                }
                    //如果没有就添加json对象
                if(!flag) {


                    var json = {
                        id: id,
                        name: $("#workerName").val(),
                        num: $("#rentNum").val(),
                        price: $("#workerPrice").val(),
                        total: parseFloat($("#workerPrice").val()) * parseInt($("#rentNum").val())
                    }
                    //添加到数组
                    this.$data.workerArray.push(json);
                }
            }

        },
        computed: {
                total:function () {
                    var result = 0;

                    for(var i ; i < this.$data.workerArray.length;i++){
                        var item = this.$data.workerArray[i];
                        alert(item.total)
                        result += item.total;
                    }
                    return result;
                },
                preCost:function () {
                    return this.total * 0.3;
                },
                lastCost:function () {
                    return this.total * 0.7;
                }
        }
    })


</script>
</body>
</html>
