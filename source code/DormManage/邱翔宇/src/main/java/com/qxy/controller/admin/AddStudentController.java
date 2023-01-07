package com.qxy.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.qxy.entity.DormBuild;
import com.qxy.entity.Student;
import com.qxy.entity.User;
import com.qxy.service.CommonService;
import com.qxy.service.SuperManagerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 21:32
 * @Introduction:
 */
@WebServlet("/user/admin/student/add")
public class AddStudentController extends HttpServlet {
    private static final long serialVersionUID = 7944541943014728510L;
    CommonService cm = new CommonService();
    SuperManagerService sms = new SuperManagerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取宿舍楼信息添加到请求中
        List<DormBuild> list = cm.getBuildingList();
        req.setAttribute("buildingList", list);
        //请求转发跳转转到添加页面
        req.getRequestDispatcher("/jsp/admin/addStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String nickname = req.getParameter("nickname");
        String password = req.getParameter("password");
        String rPassword = req.getParameter("rPassword");
        String sex = req.getParameter("sex");
        String tel = req.getParameter("tel");
        Integer role = req.getParameter("role") == null ? 2 : Integer.parseInt(req.getParameter("role"));
        Integer build = Integer.parseInt(req.getParameter("build"));
        String dormCode = req.getParameter("dorm_Code");

        //获取当前登录用户的id
        HttpSession session = req.getSession(false);
        User currUser = (User) session.getAttribute("currUser");
        Map<Object, Object> map = new ConcurrentHashMap<>(8);
        try {
            //添加宿舍管理员
            sms.addStudent(nickname, username, password, rPassword, sex, tel, build, dormCode, role, currUser.getId());
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
