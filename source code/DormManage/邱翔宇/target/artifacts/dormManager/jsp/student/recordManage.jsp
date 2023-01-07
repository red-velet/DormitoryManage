<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>缺勤记录管理</title>
    <base href="<%=basePath%>">
    <!-- 统一引入layui核心文件和jquery -->
    <jsp:include page="/jsp/common/common_link.jsp"/>
    <style>
        .layui-container {
            width: 100%;
            height: 600px;
            position: relative;
            top: 10px;
        }

        .row1 {
            position: absolute;
            width: 100%;
            top: 30px;
        }

        .row2 {
            position: absolute;
            top: 70px;
            width: 100%;
        }

        .btn-add {
            padding-left: 18px;
        }
    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <!-- 菜单栏显示 -->
    <jsp:include page="/jsp/common/common_student_menu.jsp"/>

    <!-- 内容主体区域 -->
    <div class="layui-body">
        <!-- 添加、搜索区域 -->
        <div class="layui-container ">
            <div class="row row1">
                <div class="layui-col-md4 layui-col-md-offset2">
                    <!-- 搜索框 -->
                    <form class="layui-form layui-form-pane">
                        <div class="layui-form-item" hidden>
                            <label class="layui-form-label">id</label>
                            <div class="layui-input-inline">
                                <input type="text" id="id" name="id" lay-verify="required"
                                       value="${sessionScope.currUser.id}"
                                       autocomplete="off"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">条件查询</label>
                            <div class="layui-input-inline">
                                <select name="quiz1" id="condition">
                                    <option value="">请选择查询类型</option>
                                    <option value="date" selected="">日期</option>
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="layui-col-md4" style="position: absolute;left: 42%">
                    <div class="layui-form">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <input type="text" class="layui-input" autocomplete="off" id="content">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <button id="search" style="position: absolute;left: 58%" type="button" class="layui-btn layui-btn-warm">
                    <i class="layui-icon layui-icon-search"></i> 搜索
                </button>
            </div>
            <div class="row row2">
                <table id="list" lay-filter="test"></table>
                <!-- 表头工具栏 -->
                <script type="text/html" id="toolBar">
                    <button class="layui-btn layui-btn-sm" lay-event="getCheckData">
                        获取选择行数据
                    </button>
                    <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">
                        获取选中条目数
                    </button>
                    <button class="layui-btn layui-btn-sm" lay-event="isAll">
                        验证是否被全选
                    </button>
                </script>
            </div>
        </div>
    </div>

    <!-- 页脚显示 -->
    <jsp:include page="/jsp/common/common_footer.jsp"/>

    <!-- 分页显示 -->
    <script>
        $(document).ready(function () {
            $(".recordManage").addClass("active");
        });
        layui.use(["laydate", "table"], function () {
            //获取laydate
            let laydate = layui.laydate;
            //日期时间范围
            laydate.render({
                elem: '#content'
                , type: 'date'
                , range: 'to'
                , format: 'yyyy-MM-dd'
                , min: '2000-1-01'
                , max: '2030-12-21'
            });
            //获取table实例
            let table = layui.table;
            //渲染表格
            table.render({
                elem: "#list",//通过id绑定容器
                url: "user/student/record/list",//数据接口
                where: {
                    id: $("#id").val()
                },
                cols: [[
                    {field: "no", title: "序号", type: "numbers"},
                    {field: "checkbox", type: "checkbox"},
                    {field: "rid", title: "缺勤记录编号", sort: true, edit: false, hide: true},
                    {field: "date", title: "日期", sort: true, edit: false},
                    {field: "stu_code", title: "学号", sort: true, edit: false},
                    {field: "name", title: "姓名", sort: true, edit: false},
                    {field: "sex", title: "性别", sort: true, edit: false},
                    {field: "buildName", title: "宿舍楼", sort: true, edit: false},
                    {field: "dorm_Code", title: "寝室编号", edit: false},
                    {field: "remark", title: "缺勤备注", edit: false},
                ]],
                method: "GET",
                page: {
                    first: '首页',
                    last: '尾页',
                    limits: [5, 10, 20, 30],
                    layout: ['count', 'first', 'prev', 'page', 'next', 'last', 'limit', 'skip'],
                    limit: 5,
                    groups: 5
                },
                parseData: function (reply) {
                    return {
                        "code": reply.code, //解析接口状态
                        "msg": reply.msg, //解析提示文本
                        "count": reply.count, //解析数据长度
                        "data": reply.data //解析数据列表
                    }
                },
                toolbar: "#toolBar"
            });
            //重载表格
            $("#search").click(function () {
                let searchDom = $("#condition");
                let contentDom = $("#content");
                let idDom = $("#id");

                console.log("下拉框的值为 " + searchDom.val());
                console.log("查询输入框的值为 " + contentDom.val());
                console.log("id的值为 " + idDom.val());

                table.reload("list", {
                    url: 'user/student/record/search',
                    where: {
                        condition: searchDom.val(),
                        content: contentDom.val(),
                        id: idDom.val()
                    },
                    page: {
                        curr: 1,
                        limit: 5,
                        groups: 5
                    }
                })
            });
            //监听工具类
            table.on('toolbar(test)', function (obj) {
                //在obj的config内可以获取id属性值
                console.log("obj=" + obj);
                //获取表格被选中的对象
                let checkStatus = table.checkStatus(obj.config.id);
                console.log("checkStatus=" + checkStatus)
                switch (obj.event) {
                    case "getCheckLength":
                        let len = checkStatus.data.length;
                        layer.msg("已选择" + len + "条记录");
                        break
                    case "isAll":
                        let flag = checkStatus.isAll;
                        layer.msg(flag ? "全选" : "未全选");
                        break;
                    case "getCheckData":
                        let arr = JSON.stringify(checkStatus.data);
                        layer.msg("选中的数据+" + arr);
                    default:
                        break;
                }
            })
        });
    </script>
</div>
</body>
</html>
