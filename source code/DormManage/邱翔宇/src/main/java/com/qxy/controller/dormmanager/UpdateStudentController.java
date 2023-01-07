package com.qxy.controller.dormmanager;

import com.alibaba.fastjson.JSONObject;
import com.qxy.entity.DormBuild;
import com.qxy.entity.Student;
import com.qxy.service.CommonService;
import com.qxy.service.SuperManagerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 22:07
 * @Introduction:
 */
@WebServlet("/user/dormManager/student/update")
public class UpdateStudentController extends HttpServlet {
    private static final long serialVersionUID = -2806443139668395533L;
    SuperManagerService sms = new SuperManagerService();
    CommonService cs = new CommonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = req.getParameter("id") == null ? -1 : Integer.parseInt(req.getParameter("id"));
        //获取正需要修改的学生信息,将数据添加到请求中,然后转发到修改界面
        Student student = sms.getStudentById(id);
        System.out.println("正在修改: " + student);
        req.setAttribute("updateStudent", student);
        List<DormBuild> buildingList = cs.getBuildingList();
        req.setAttribute("buildList", buildingList);
        req.getRequestDispatcher("/jsp/dormManager/updateStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String nickname = req.getParameter("nickname");
        String password = req.getParameter("password");
        String rPassword = req.getParameter("rPassword");
        String sex = req.getParameter("sex");
        String tel = req.getParameter("tel");
        Integer role = req.getParameter("role") == null ? 1 : Integer.parseInt(req.getParameter("role"));
        String dormCode = req.getParameter("dorm_Code");
        Integer buildId = Integer.parseInt(req.getParameter("build"));

        Map<Object, Object> map = new ConcurrentHashMap<>(8);
        try {
            //修改学生信息
            sms.updateStudent(id, nickname, username, password, rPassword, dormCode, buildId, sex, tel, role);
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
