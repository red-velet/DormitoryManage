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
 * @Date: 2023/1/4 17:22
 * @Introduction:
 */
@WebServlet("/user/admin/dormManager/delete")
public class DeleteDormAdminController extends HttpServlet {
    private static final long serialVersionUID = -192222469871049291L;
    SuperManagerService sms = new SuperManagerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        sms.deleteDormManagerById(id);
        resp.sendRedirect(req.getContextPath() + "/jsp/admin/dormAdminManage.jsp");
    }
}
