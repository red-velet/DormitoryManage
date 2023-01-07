package com.qxy.controller.dormmanager;

import com.alibaba.fastjson.JSONObject;
import com.qxy.constant.SearchType;
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
 * @Date: 2023/1/4 22:53
 * @Introduction:
 */
@WebServlet("/user/dormManager/student/search")
public class SearchStudentController extends HttpServlet {
    private static final long serialVersionUID = -1208458590637441957L;
    CommonService cs = new CommonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String condition = req.getParameter("condition");
        String content = req.getParameter("content");
        Integer currPage = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
        Integer pageSize = req.getParameter("limit") == null ? 3 : Integer.parseInt(req.getParameter("limit"));
        //搜索类型为昵称搜索
        List<Student> list = null;
        long count = 0;
        if (SearchType.NICKNAME.equals(condition)) {
            list = cs.getStudentByNickname(content, currPage, pageSize);
            count = cs.countStudentByNickname(content);
        } else if (SearchType.SEX.equals(condition)) {
            //搜索类型为性别搜索
            list = cs.getStudentBySex(content, currPage, pageSize);
            count = cs.countStudentBySex(content);
        }
        String reply = JSONObject.toJSONString(LayuiResponseUtil.pattern(null, null, list, count));
        //返回给前端
        resp.setHeader("Content-Type", "application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print(reply);
        writer.flush();
    }
}
