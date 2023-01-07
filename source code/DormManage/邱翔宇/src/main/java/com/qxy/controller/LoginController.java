package com.qxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.qxy.entity.User;
import com.qxy.service.CommonService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 10:38
 * @Introduction:
 */
@WebServlet("/user/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = -6241266918934303516L;
    CommonService cm = new CommonService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("请求登录: " + username + "-" + password);

        //进行登录验证
        Map<Object, Object> map = new ConcurrentHashMap<>(8);
        try {
            User currUser = cm.login(username, password);
            map.put("message", "success");
            map.put("role", currUser.getRole_id());
            System.out.println("登录成功: " + username + "-" + password);
            //创建session保存登录信息
            HttpSession session = req.getSession();
            session.setAttribute("currUser", currUser);
        } catch (RuntimeException e) {
            System.out.println("登录失败: " + e.getMessage());
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
