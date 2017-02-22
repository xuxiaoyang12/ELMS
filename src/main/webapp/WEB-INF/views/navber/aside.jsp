<%--
  Created by IntelliJ IDEA.
  User: Mxia
  Date: 2017/1/23
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-pie-chart"></i>
                    <span>财务报表</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="../charts/chartjs.html"><i class="fa fa-circle-o"></i> 日报</a></li>
                    <li><a href="../charts/morris.html"><i class="fa fa-circle-o"></i> 月报</a></li>
                    <li><a href="../charts/flot.html"><i class="fa fa-circle-o"></i> 年报</a></li>
                </ul>
            </li>

            <li class="treeview ${fn:startsWith(param.menu,'device_') ? 'active' : ''} ">
                <a href="/device/menu">
                    <i class="fa fa-edit"></i> <span>业务</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu ${fn:startsWith(param.menu,'device_') ? 'active' : ''}">
                    <li class="${fn:startsWith(param.menu,'device_rent') ? 'active' : ''}">
                        <a href="#">
                        <i class="fa fa-circle-o"></i> 设备租赁<i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <li class="${param.menu == 'device_rent_list' ? 'active' : ''}">
                                <a href="/device/rent/list"><i class="fa fa-list-alt"></i> 业务流水</a>
                            </li>
                            <li class="${param.menu == 'device_rent_add' ? 'active' : ''}">
                                <a href="/device/rent/add"><i class="fa fa-plus"></i> 新增流水 </a>

                            </li>
                        </ul>
                    </li>

                    <li class="${fn:startsWith(param.menu,'device_manage_') ? 'active' : ''}">
                        <a href="#"><i class="fa fa-circle-o"></i> 设备管理<i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="${param.menu == 'device_manage_list' ? 'active' : ''}">
                                <a href="/device/manage/list"><i class="fa fa-list-ul"></i> 设备库存</a>
                            </li>
                            <li class="${param.menu == 'device_manage_add' ? 'active' : ''}">
                                <a href="/device/manage/add"><i class="fa fa-plus"></i> 新增设备 </a>

                            </li>
                        </ul>
                    </li>

                    <li class="${fn:startsWith(param.menu,'device_workout') ? 'active' : ''}">
                        <a href="#"><i class="fa fa-circle-o"></i> 劳务外包 <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="#"><i class="fa fa-list-alt"></i> 业务流水</a></li>
                            <li class="${param.menu == 'device_workout_add' ? 'active' : ''}">
                                <a href="/device/workOut/add"><i class="fa fa-plus"></i> 新增流水 <i class="fa fa-angle-left pull-right"></i></a>

                            </li>
                        </ul>
                    </li>

                </ul>
            </li>
            <li class="treeview ${param.menu == 'pan' ? 'active' : ''}">
                <a href="/pan/list">
                    <i class="fa fa-folder"></i>
                    <span>公司网盘</span>
                </a>

            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
