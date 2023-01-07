package com.qxy.controller.dormmanager;

import com.alibaba.fastjson.JSONObject;
import com.qxy.entity.RecordPlus;
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
 * @Date: 2023/1/5 1:38
 * @Introduction:
 */
@WebServlet("/user/dormManager/record/update")
public class UpdateRecordController extends HttpServlet {
    private static final long serialVersionUID = 2882145007432526763L;
    SuperManagerService sms = new SuperManagerService();
    CommonService cs = new CommonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer rid = req.getParameter("id") == null ? -1 : Integer.parseInt(req.getParameter("id"));
        //获取正需要修改的缺勤记录,将数据添加到请求中,然后转发到修改界面
        RecordPlus recordPlus = sms.getRecordByRid(rid);
        req.setAttribute("updateRecord", recordPlus);
        req.getRequestDispatcher("/jsp/dormManager/updateRecord.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stuCode = req.getParameter("stu_code");
        String date = req.getParameter("date");
        String remark = req.getParameter("remark");
        Integer rid = req.getParameter("rid") == null ? -1 : Integer.parseInt(req.getParameter("rid"));
        System.out.println("stuCode = " + stuCode);
        System.out.println("date = " + date);
        System.out.println("remark = " + remark);
        System.out.println("rid = " + rid);
        System.out.println("req.getParameter(\"rid\") = " + req.getParameter("rid"));

        Map<Object, Object> map = new ConcurrentHashMap<>(8);
        try {
            //修改缺勤记录信息
            sms.updateRecord(rid, stuCode, date, remark);
            map.put("message", "success");
        } catch (RuntimeException e) {
            System.out.println("修改失败-" + e.getMessage());
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
