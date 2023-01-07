<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>添加宿舍管理员</title>
    <base href="<%=basePath%>">
    <!-- 统一引入layui核心文件和jquery -->
    <jsp:include page="/jsp/common/common_link.jsp"/>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <!-- 菜单栏显示 -->
    <jsp:include page="/jsp/common/common_admin_menu.jsp"/>

    <!-- 内容主体区域 -->
    <div class="layui-body">
        <div class="layui-container" style="margin-top: 15px">
            <form class="layui-form layui-form-pane">
                <div class="layui-form-item">
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="nickname" lay-verify="required" placeholder="请输入昵称" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="username" lay-verify="required" placeholder="请输入用户名" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="password" lay-verify="required" placeholder="请输入密码"
                               autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">请务必填写密码</div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">重复密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="rPassword" lay-verify="required" placeholder="请再次输入密码"
                               autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">请务必填写密码</div>
                </div>
                <div class="layui-form-item" pane="">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-block">
                        <input type="radio" name="sex" value="男" title="男" checked="">
                        <input type="radio" name="sex" value="女" title="女">
                        <input type="radio" name="sex" value="禁" title="禁用" disabled="">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">管理楼栋</label>
                    <div class="layui-input-block">
                        <c:forEach var="build" items="${requestScope.buildingList}">
                            <input type="checkbox" name="build" value="${build.id}" title="${build.name}">
                        </c:forEach>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">联系电话</label>
                    <div class="layui-input-inline">
                        <input type="text" name="tel" lay-verify="required" placeholder="请输入电话" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item" hidden>
                    <label class="layui-form-label">角色</label>
                    <div class="layui-input-inline">
                        <select name="role" lay-verify="required">
                            <option value="" selected="">请选择角色</option>
                            <option value="1" selected>宿舍管理员</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn" type="button" lay-submit="" lay-filter="addDormManager"><i
                            class="layui-icon layui-icon-ok"></i>确认添加
                    </button>
                    <button class="layui-btn " type="reset"><i class="layui-icon layui-icon-refresh"></i>重置</button>
                </div>
            </form>
        </div>
    </div>

    <!-- 页脚显示 -->
    <jsp:include page="/jsp/common/common_footer.jsp"/>
    <!-- 表单输入验证 -->
    <script>
        $(document).ready(function () {
            $(".dormAdminManage").addClass("active");
        });
        //表单和弹出层的使用
        layui.use(['form', 'layer'], function () {
            let form = layui.form;
            let layer2 = layui.layer;


            // 异步进行添加宿舍管理员操作
            form.on('submit(addDormManager)', function (data) {
                data = data.field;
                console.log(data.build);
                let check_box = [];
                $('input[type=checkbox]:checked').each(function () {
                    check_box.push($(this).val());
                });
                let build = check_box.toString();
                console.log("builds" + build)
                //输入验证
                if (data.username === '') {
                    layer.msg('用户名不能为空');
                    return false;
                }
                if (data.password === '') {
                    layer.msg('密码不能为空');
                    return false;
                }
                if (data.rPassword === '') {
                    layer.msg('重复密码不能为空');
                    return false;
                }
                if (data.rPassword !== data.password) {
                    layer.msg('两次密码要求一致');
                    return false;
                }
                add(data.nickname, data.username, data.password, data.rPassword, data.sex, data.tel, build, data.role);
            });

            function add(nickname, username, password, rPassword, sex, tel, build, role) {
                $.post('user/admin/dormManager/add',
                    {
                        "nickname": nickname,
                        "username": username,
                        "password": password,
                        "rPassword": rPassword,
                        "sex": sex,
                        "tel": tel,
                        "build": build,
                        "role": role
                    },
                    function (reply) {
                        if (reply.message === "success") {
                            layer2.msg("添加成功");
                            layer2.msg("添加成功");
                            layer2.msg("添加成功");
                            layer2.msg("添加成功");
                            layer2.msg("添加成功");
                            layer2.msg("添加成功");
                            layer2.msg("添加成功");
                            location.href = "jsp/admin/dormAdminManage.jsp";
                        } else if (reply.message === "fail") {
                            layer2.msg("添加失败: " + reply.info);
                        } else {
                            layer2.msg("添加失败", {
                                time: 5000
                            });
                        }
                    });
            }
        })
    </script>
</div>
</body>
</html>

