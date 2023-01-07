package com.qxy.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.qxy.entity.DormBuild;
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
 * @Date: 2023/1/4 23:22
 * @Introduction:
 */
@WebServlet("/user/admin/build/add")
public class AddBuildController extends HttpServlet {
    private static final long serialVersionUID = -168491660939564458L;
    CommonService cm = new CommonService();
    SuperManagerService sms = new SuperManagerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取宿舍楼信息添加到请求中
        List<DormBuild> list = cm.getBuildingList();
        req.setAttribute("buildingList", list);
        //请求转发跳转转到添加页面
        req.getRequestDispatcher("/jsp/admin/addBuild.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String remark = req.getParameter("remark");

        Map<Object, Object> map = new ConcurrentHashMap<>(8);
        try {
            //添加宿舍楼
            sms.addBuild(name, remark);
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
