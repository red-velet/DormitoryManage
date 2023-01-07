package com.qxy.controller.student;

import com.alibaba.fastjson.JSONObject;
import com.qxy.constant.Role;
import com.qxy.entity.RecordPlus;
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
 * @Date: 2023/1/5 0:44
 * @Introduction:
 */
@WebServlet("/user/student/record/list")
public class ListRecordController extends HttpServlet {
    private static final long serialVersionUID = -1503590088874756997L;
    SuperManagerService sms = new SuperManagerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("请求自己的缺勤记录");
        System.out.println(req.getParameter("id"));
        Integer currPage = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
        Integer pageSize = req.getParameter("limit") == null ? 3 : Integer.parseInt(req.getParameter("limit"));
        Integer id = Integer.parseInt(req.getParameter("id"));
        //分页获取自己的缺勤记录的所有信息数据集合
        List<RecordPlus> list = sms.getMyAllRecordList(id, Role.STUDENT, currPage, pageSize);
        //获取宿舍管理员的总数据条数
        long count = sms.countMyAllRecordList(id, Role.STUDENT);
        //转换成符合layui的格式
        String reply = JSONObject.toJSONString(LayuiResponseUtil.pattern(null, null, list, count));
        //返回给前端
        resp.setHeader("Content-Type", "application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print(reply);
        writer.flush();
    }
}
