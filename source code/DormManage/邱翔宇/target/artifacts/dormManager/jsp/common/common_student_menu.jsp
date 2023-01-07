<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-header">
    <div class="layui-logo layui-hide-xs layui-bg-black">校园超市管理系统</div>
    <!-- 头部区域（可配合layui 已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
        <li class="layui-nav-item layui-hide-xs"><a href="user/student/main">首页</a></li>
        <li class="layui-nav-item layui-hide-xs"><a href="user/student/info">个人信息</a></li>
    </ul>
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item layui-hide layui-show-md-inline-block">
            <a href="javascript:;">
                <img src="static/image/bg.jpg" class="layui-nav-img">
                ${sessionScope.currUser.name}
            </a>
            <dl class="layui-nav-child">
                <dd><a href="user/student/info">个人信息</a></dd>
                <dd><a href="user/exit">登出</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item" lay-header-event="menuRight" lay-unselect>
            <a href="javascript:;">
                <i class="layui-icon layui-icon-more-vertical"></i>
            </a>
        </li>
    </ul>
</div>

<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">

        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree" lay-filter="test">
            <li class="layui-nav-item">
                <a class="recordManage" href="jsp/student/recordManage.jsp">缺勤记录管理</a>
            </li>
            <li class="layui-nav-item">
                <a class="myInfoManage" href="user/student/info">个人信息管理</a>
            </li>
            <li class="layui-nav-item">
                <a class="exit" href="user/exit">退出系统</a>
            </li>
        </ul>
    </div>
</div>