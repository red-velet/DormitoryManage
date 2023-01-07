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
    <title>添加缺勤记录</title>
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
                    <label class="layui-form-label">学号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="stu_code" lay-verify="required" placeholder="请填写学生学号" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">日期</label>
                    <div class="layui-input-inline">
                        <div class="layui-inline">
                            <div class="layui-input-inline">
                                <input type="text" name="date" class="layui-input" id="recordDate">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-inline">
                        <textarea name="remark" required lay-verify="required" placeholder="请填写缺勤备注"
                                  class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn" type="button" lay-submit="" lay-filter="addRecord"><i
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
            $(".recordManage").addClass("active");
        });
        //表单和弹出层的使用
        layui.use(['form', 'layer','laydate'], function () {
            //获取表单实例
            let form = layui.form;
            //获取弹出层
            let layer2 = layui.layer;
            //获取日期实例
            let laydate = layui.laydate;
            //日期时间范围
            laydate.render({
                elem: '#recordDate'
                ,type: 'date'
                ,range: false
                ,format: 'yyyy-MM-dd'
                ,min: '2000-1-01'
                ,max: '2030-12-21'
            });

            //异步进行添加学生操作
            form.on('submit(addRecord)', function (data) {
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
                add(data.stu_code, data.date, data.remark);
            });

            function add(stu_code, date,remark) {
                $.post('user/admin/record/add',
                    {
                        "stu_code": stu_code,
                        "date": date,
                        "remark": remark
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
                            location.href = "jsp/admin/recordManage.jsp";
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
