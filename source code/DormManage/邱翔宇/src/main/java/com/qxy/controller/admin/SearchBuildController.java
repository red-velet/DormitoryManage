package com.qxy.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.qxy.constant.SearchType;
import com.qxy.entity.DormBuild;
import com.qxy.entity.Student;
import com.qxy.service.CommonService;
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
 * @Date: 2023/1/5 0:11
 * @Introduction:
 */
@WebServlet("/user/admin/build/search")
public class SearchBuildController extends HttpServlet {
    private static final long serialVersionUID = 8325609111625353716L;
    CommonService cs = new CommonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String condition = req.getParameter("condition");
        String content = req.getParameter("content");
        Integer currPage = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
        Integer pageSize = req.getParameter("limit") == null ? 3 : Integer.parseInt(req.getParameter("limit"));
        //搜索类型为昵称搜索
        List<DormBuild> list = null;
        long count = 0;
        if (SearchType.NICKNAME.equals(condition)) {
            list = cs.getBuildByNickname(content, currPage, pageSize);
            count = cs.countBuildByNickname(content);
        }
        String reply = JSONObject.toJSONString(LayuiResponseUtil.pattern(null, null, list, count));
        //返回给前端
        resp.setHeader("Content-Type", "application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print(reply);
        writer.flush();
    }
}
