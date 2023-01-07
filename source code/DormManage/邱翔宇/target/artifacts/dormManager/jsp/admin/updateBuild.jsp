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
    <title>修改宿舍楼</title>
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
                <div class="layui-form-item" hidden>
                    <label class="layui-form-label">id</label>
                    <div class="layui-input-inline">
                        <input type="text" name="id" lay-verify="required" value="${requestScope.updateBuild.id}"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="name" lay-verify="required" value="${requestScope.updateBuild.name}"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">简介</label>
                    <div class="layui-input-inline">
                        <textarea name="remark" required lay-verify="required"
                                  class="layui-textarea">${requestScope.updateBuild.remark}</textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn" type="button" lay-submit="" lay-filter="updateBuild"><i
                            class="layui-icon layui-icon-ok"></i>确认修改
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
            $(".buildManage").addClass("active");
        });
        layui.use(['form', 'layer'], function () {
            let form = layui.form;
            let layer2 = layui.layer;
            // 异步进行添加宿舍管理员操作
            form.on('submit(updateBuild)', function (data) {
                data = data.field;
                //输入验证
                if (data.name === '') {
                    layer.msg('宿舍楼名称不能为空');
                    return false;
                }
                if (data.remark === '') {
                    layer.msg('宿舍楼简介不能为空');
                    return false;
                }
                update(data.id, data.name, data.remark);
            });

            function update(id, name, remark) {
                $.post('user/admin/build/update',
                    {
                        "id": id,
                        "name": name,
                        "remark": remark
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
                            location.href = "jsp/admin/buildManage.jsp";
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
