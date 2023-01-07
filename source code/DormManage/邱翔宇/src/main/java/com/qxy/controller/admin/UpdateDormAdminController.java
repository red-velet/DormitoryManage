package com.qxy.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.qxy.entity.DormBuild;
import com.qxy.entity.UserAndBuildPlus;
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
 * @Date: 2023/1/4 16:14
 * @Introduction:
 */
@WebServlet("/user/admin/dormManager/update")
public class UpdateDormAdminController extends HttpServlet {
    private static final long serialVersionUID = 5328213868002219076L;
    SuperManagerService sms = new SuperManagerService();
    CommonService cs = new CommonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("修改id请求转发到修改界面");
        Integer id = req.getParameter("id") == null ? -1 : Integer.parseInt(req.getParameter("id"));
        UserAndBuildPlus user = sms.getDormById(id);
        req.setAttribute("updateUser", user);
        List<DormBuild> buildingList = cs.getBuildingList();
        req.setAttribute("buildList", buildingList);
        req.getRequestDispatcher("/jsp/admin/updateDormAdmin.jsp").forward(req, resp);
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
        String build = req.getParameter("build");

        Map<Object, Object> map = new ConcurrentHashMap<>(8);
        try {
            //修改宿舍管理员信息
            sms.updateDormManager(id, username, password, rPassword, sex, tel, role, build, nickname);
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
