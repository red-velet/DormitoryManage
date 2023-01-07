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
    <title>修改缺勤记录</title>
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
                        <input type="text" name="rid" lay-verify="required" value="${requestScope.updateRecord.rid}"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">学号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="stu_code" lay-verify="required" value="${requestScope.updateRecord.stu_code}" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">日期</label>
                    <div class="layui-input-inline">
                        <div class="layui-inline">
                            <div class="layui-input-inline">
                                <input type="text" name="date" value="${requestScope.updateRecord.date}" class="layui-input" id="recordDate">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-inline">
                        <textarea name="remark" required lay-verify="required"
                                  class="layui-textarea">${requestScope.updateRecord.remark}</textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn" type="button" lay-submit="" lay-filter="updateRecord"><i
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
            $(".recordManage").addClass("active");
        });
        layui.use(['form', 'layer'], function () {
            let form = layui.form;
            let layer2 = layui.layer;
            // 异步进行添加宿舍管理员操作
            form.on('submit(updateRecord)', function (data) {
                data = data.field;
                //输入验证
                if (data.stu_code === '') {
                    layer.msg('学号不能为空');
                    return false;
                }
                if (data.date === '') {
                    layer.msg('日期不能为空');
                    return false;
                }
                if (data.remark === '') {
                    layer.msg('备注不能为空');
                    return false;
                }
                update(data.rid, data.stu_code,data.date, data.remark);
            });

            function update(rid, stu_code, date,remark) {
                $.post('user/dormManager/record/update',
                    {
                        "rid": rid,
                        "stu_code": stu_code,
                        "date": date,
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
                            location.href = "jsp/dormManager/recordManage.jsp";
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

