package com.qxy.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.qxy.constant.SearchType;
import com.qxy.entity.DormBuild;
import com.qxy.entity.RecordPlus;
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
 * @Date: 2023/1/5 2:12
 * @Introduction:
 */
@WebServlet("/user/admin/record/search")
public class SearchRecordController extends HttpServlet {
    private static final long serialVersionUID = 5463281112466082461L;
    CommonService cs = new CommonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String condition = req.getParameter("condition");
        String content = req.getParameter("content");
        Integer currPage = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
        Integer pageSize = req.getParameter("limit") == null ? 3 : Integer.parseInt(req.getParameter("limit"));
        //搜索类型为日期段搜索
        List<RecordPlus> list = null;
        long count = 0;
        if (SearchType.DATE.equals(condition)) {
            list = cs.getRecordByDate(content, currPage, pageSize);
            count = cs.countRecordByDate(content);
        }
        String reply = JSONObject.toJSONString(LayuiResponseUtil.pattern(null, null, list, count));
        //返回给前端
        resp.setHeader("Content-Type", "application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print(reply);
        writer.flush();
    }
}
