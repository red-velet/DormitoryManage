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
    <title>个人信息设置</title>
    <base href="<%=basePath%>">
    <!-- 统一引入layui核心文件和jquery -->
    <jsp:include page="/jsp/common/common_link.jsp"/>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <!-- 菜单栏显示 -->
    <jsp:include page="/jsp/common/common_dormManager_menu.jsp"/>

    <!-- 内容主体区域 -->
    <div class="layui-body">
        <div class="layui-container" style="margin-top: 15px">
            <form class="layui-form layui-form-pane">
                <div class="layui-form-item" hidden>
                    <label class="layui-form-label">id</label>
                    <div class="layui-input-inline">
                        <input type="text" name="id" lay-verify="required" value="${sessionScope.currUser.id}"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="name" lay-verify="required" value="${sessionScope.currUser.name}"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="stu_code" lay-verify="required"
                               value="${sessionScope.currUser.stu_code}"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="password" lay-verify="required"
                               value="${sessionScope.currUser.passWord}"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">重复密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="rPassword" lay-verify="required"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item" pane="">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-block">
                        <input type="radio" name="sex" value="男"
                               title="男" ${sessionScope.currUser.sex eq "男" ? "checked" : ""}>
                        <input type="radio" name="sex" value="女"
                               title="女" ${sessionScope.currUser.sex eq "女" ? "checked" : ""}>
                        <input type="radio" name="sex" value="禁" title="禁用" disabled="">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">联系电话</label>
                    <div class="layui-input-inline">
                        <input type="text" name="tel" lay-verify="required" value="${sessionScope.currUser.tel}"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn" type="button" lay-submit="" lay-filter="updateMyInfo"><i
                            class="layui-icon layui-icon-ok"></i>确认保存
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
            $(".myInfoManage").addClass("active");
        });
        layui.use(['form', 'layer'], function () {
            let form = layui.form;
            let layer2 = layui.layer;
            // 异步进行添加宿舍管理员操作
            form.on('submit(updateMyInfo)', function (data) {
                data = data.field;
                //输入验证
                if (data.name === '') {
                    layer.msg('姓名不能为空');
                    return false;
                }
                if (data.stu_code === '') {
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
                update(data.id, data.name, data.stu_code, data.password, data.rPassword, data.sex, data.tel);
            });

            function update(id, name, stu_code, password, rPassword, sex, tel) {
                $.post('user/dormManager/info',
                    {
                        "id": id,
                        "name": name,
                        "stu_code": stu_code,
                        "password": password,
                        "rPassword": rPassword,
                        "sex": sex,
                        "tel": tel,
                    },
                    function (reply) {
                        if (reply.message === "success") {
                            layer2.msg("修改成功");
                            layer2.msg("修改成功");
                            layer2.msg("修改成功");
                            layer2.msg("修改成功");
                            layer2.msg("修改成功");
                            layer2.msg("修改成功");
                            layer2.msg("修改成功");
                            layer2.msg("修改成功");
                            layer2.msg("修改成功");
                            layer2.msg("修改成功");
                            layer2.msg("修改成功");
                            location.href = "index.jsp";
                        } else if (reply.message === "fail") {
                            layer2.msg("修改失败: " + reply.info);
                        } else {
                            layer2.msg("修改失败", {
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

