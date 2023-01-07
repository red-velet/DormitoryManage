package com.qxy.controller.admin;

import com.qxy.service.SuperManagerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 23:48
 * @Introduction:
 */
@WebServlet("/user/admin/build/delete")
public class DeleteBuildController extends HttpServlet {
    private static final long serialVersionUID = 5984369832908733883L;
    SuperManagerService sms = new SuperManagerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        sms.deleteBuildById(id);
        resp.sendRedirect(req.getContextPath() + "/jsp/admin/buildManage.jsp");
    }
}
