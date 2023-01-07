package com.qxy.controller.admin;

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
 * @Date: 2023/1/4 23:40
 * @Introduction:
 */
@WebServlet("/user/admin/build/update")
public class UpdateBuildController extends HttpServlet {
    private static final long serialVersionUID = 2575529164915474716L;
    SuperManagerService sms = new SuperManagerService();
    CommonService cs = new CommonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = req.getParameter("id") == null ? -1 : Integer.parseInt(req.getParameter("id"));
        //获取正需要修改的宿舍楼,将数据添加到请求中,然后转发到修改界面
        DormBuild build = sms.getBuildById(id);
        req.setAttribute("updateBuild", build);
        req.getRequestDispatcher("/jsp/admin/updateBuild.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String remark = req.getParameter("remark");

        Map<Object, Object> map = new ConcurrentHashMap<>(8);
        try {
            //修改宿舍楼信息
            sms.updateBuild(id,name,remark);
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
