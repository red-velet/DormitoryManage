<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>超级管理员主界面</title>
    <base href="<%=basePath%>">
    <jsp:include page="/jsp/common/common_link.jsp"/>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <!-- 页头-头部菜单和左侧菜单栏 -->
    <jsp:include page="/jsp/common/common_admin_menu.jsp"/>

    <!-- 内容主体区域 -->
    <div class="layui-body">
        <!-- 轮播图 -->
        <div style="padding: 15px;">
            <div class="layui-carousel" id="carousel">
                <div carousel-item="" style="border-radius:15px ;">
                    <img src="static/image/lunbo01.jpg" class="pic" style="width: 100%;height: auto"/>
                    <img src="static/image/lunbo02.jpg" class="pic" style="width: 100%;height: auto"/>
                    <img src="static/image/lunbo03.jpg" class="pic" style="width: 100%;height: auto"/>
                </div>
            </div>
        </div>
        <!-- 倒计时 -->
        <div id="test"></div>
        <!-- 标题 -->
        <h1 style="position: absolute;left: 33%;bottom: 180px;">欢迎登录学生宿舍管理系统</h1>
    </div>

    <!-- 页脚-底部固定区域 -->
    <div class="layui-footer">
        ©项目制作人 计信2015班 邱翔宇
        <span class="padding-5">|</span>
        <a target="_blank" href="https://www.hbnu.edu.cn/">湖北师范大学</a>
    </div>
</div>
<!-- 轮播图 -->
<script>
    layui.use(['carousel'], function () {
        let carousel = layui.carousel;
        let i = 0
        let width = $(".pic")[i].width //获取宽度
        let height = $(".pic")[i].height //获取高度
        let ins = carousel.render({
            elem: '#carousel',
            width: width, //设置容器宽度
            height: height,
            arrow: 'hover', //始终显示箭头
            anim: 'default', //切换动画方式
            interval: 3000
        });
        carousel.on('change(carofilter)', function (obj) { //监听轮播
            i = obj.index
            width = $(".img")[i].width
            height = $(".img")[i].height
            ins.reload({ //重置轮播
                elem: '#carousel',
                width: width, //设置容器宽度
                height: height,
                arrow: 'hover', //始终显示箭头
                anim: 'default', //切换动画方式
                interval: 3000
            });
        });
    });
</script>
<!-- 倒计时 -->
<script>
    layui.use('util', function () {
        let util = layui.util;

        //示例
        let serverTime = new Date().getTime(); //假设为当前服务器时间
        let now = new Date();
        console.log("now=" + now);
        let year = now.getFullYear();
        let month = now.getMonth();
        let day = now.getDay() + 2;
        let hour = now.getHours();


        let nextDay = new Date(year, month, day);
        console.log("nextDay=" + nextDay);
        let endTime = nextDay.getTime();  //假设为结束日期

        // 倒计时60s  serverTime+60000
        util.countdown(endTime, serverTime, function (date, serverTime, timer) {
            let str = date[1] + '时' + date[2] + '分' + date[3] + '秒';
            layui.$('#test').html('<h2 style="margin-left: 33%;">距离今天结束还有：' + str + "</h2>");
        });
    });

</script>
<!-- 菜单栏 -->
<script>
    //JS
    layui.use(['element', 'layer', 'util'], function () {
        let element = layui.element
            , layer = layui.layer
            , util = layui.util
            , $ = layui.$;

        //头部事件
        util.event('lay-header-event', {
            //左侧菜单事件
            menuLeft: function (othis) {
                layer.msg('展开左侧菜单的操作', {icon: 0});
            }
            , menuRight: function () {
                layer.open({
                    type: 1
                    , content: '<div style="padding: 15px;">处理右侧面板的操作</div>'
                    , area: ['260px', '100%']
                    , offset: 'rt' //右上角
                    , anim: 5
                    , shadeClose: true
                });
            }
        });

    });
</script>
</body>
</html>