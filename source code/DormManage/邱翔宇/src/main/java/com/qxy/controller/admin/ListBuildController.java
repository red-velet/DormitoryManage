package com.qxy.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.qxy.constant.Role;
import com.qxy.entity.DormBuild;
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
 * @Date: 2023/1/4 23:13
 * @Introduction:
 */
@WebServlet("/user/admin/build/list")
public class ListBuildController extends HttpServlet {
    private static final long serialVersionUID = 7762749602883323493L;
    SuperManagerService sms = new SuperManagerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer currPage = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
        Integer pageSize = req.getParameter("limit") == null ? 3 : Integer.parseInt(req.getParameter("limit"));
        //分页获取所有宿舍楼信息数据集合
        List<DormBuild> list = sms.getAllBuildList(currPage, pageSize);
        //获取所有宿舍楼的总数据条数
        long count = sms.countAllBuildList();
        //转换成符合layui的格式
        String reply = JSONObject.toJSONString(LayuiResponseUtil.pattern(null, null, list, count));
        //返回给前端
        resp.setHeader("Content-Type", "application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print(reply);
        writer.flush();
    }
}
