package com.qxy.controller.dormmanager;

import com.alibaba.fastjson.JSONObject;
import com.qxy.service.CommonService;
import com.qxy.service.SuperManagerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: SayHello
 * @Date: 2023/1/5 1:09
 * @Introduction:
 */
@WebServlet("/user/dormManager/record/add")
public class AddRecordController extends HttpServlet {
    private static final long serialVersionUID = 1089171445875699657L;
    CommonService cm = new CommonService();
    SuperManagerService sms = new SuperManagerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //请求转发跳转转到添加页面
        req.getRequestDispatcher("/jsp/dormManager/addRecord.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stuCode = req.getParameter("stu_code");
        String date = req.getParameter("date");
        String remark = req.getParameter("remark");
        System.out.println("date = " + date);

        Map<Object, Object> map = new ConcurrentHashMap<>(8);
        try {
            //添加宿舍楼
            sms.addRecord(stuCode, date, remark);
            map.put("message", "success");
        } catch (RuntimeException e) {
            System.out.println("添加失败-" + e.getMessage());
            map.put("message", "fail");
            map.put("info", e.getMessage());
        } finally {
            //发送信息告知ajax
            resp.setHeader("Content-Type", "application/json;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            String s = JSONObject.toJSONString(map);
            writer.print(s);
            writer.flush();
        }
    }
}
