package com.qxy.controller.student;

import com.alibaba.fastjson.JSONObject;
import com.qxy.constant.Constant;
import com.qxy.service.CommonService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: SayHello
 * @Date: 2023/1/5 2:40
 * @Introduction:
 */
@WebServlet("/user/student/info")
public class InfoController extends HttpServlet {
    private static final long serialVersionUID = 3377030778053484344L;
    CommonService cs = new CommonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/student/studentInfo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String stuCode = req.getParameter("stu_code");
        String password = req.getParameter("password");
        String rPassword = req.getParameter("rPassword");
        String sex = req.getParameter("sex");
        String tel = req.getParameter("tel");
        Map<Object, Object> map = new ConcurrentHashMap<>(8);
        try {
            //修改个人的信息
            cs.updateInfo(id, name, stuCode, password, rPassword, sex, tel);
            //修改成功清除session,重新登录
            HttpSession session = req.getSession(false);
            session.removeAttribute(Constant.CURR_USER);
            session.invalidate();
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
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
