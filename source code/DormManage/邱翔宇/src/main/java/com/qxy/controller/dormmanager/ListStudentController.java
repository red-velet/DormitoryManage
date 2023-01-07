package com.qxy.controller.dormmanager;

import com.alibaba.fastjson.JSONObject;
import com.qxy.constant.Role;
import com.qxy.entity.Student;
import com.qxy.service.SuperManagerService;
import com.qxy.utils.LayuiResponseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 21:14
 * @Introduction:
 */
@WebServlet("/user/dormManager/student/list")
public class ListStudentController extends HttpServlet {
    private static final long serialVersionUID = -565485657965258334L;
    SuperManagerService sms = new SuperManagerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer currPage = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
        Integer pageSize = req.getParameter("limit") == null ? 3 : Integer.parseInt(req.getParameter("limit"));
        //分页获取学生的所有信息数据集合
        List<Student> list = sms.getAllStudentList(Role.STUDENT, currPage, pageSize);
        //获取宿舍管理员的总数据条数
        long count = sms.getAllStudentCount(Role.STUDENT);
        //转换成符合layui的格式
        String reply = JSONObject.toJSONString(LayuiResponseUtil.pattern(null, null, list, count));
        //返回给前端
        resp.setHeader("Content-Type", "application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print(reply);
        writer.flush();
    }
}
