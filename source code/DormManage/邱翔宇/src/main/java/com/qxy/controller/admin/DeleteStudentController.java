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
 * @Date: 2023/1/4 22:39
 * @Introduction:
 */
@WebServlet("/user/admin/student/delete")
public class DeleteStudentController extends HttpServlet {
    private static final long serialVersionUID = -5155366245107083365L;
    SuperManagerService sms = new SuperManagerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        sms.deleteStudentById(id);
        resp.sendRedirect(req.getContextPath() + "/jsp/admin/studentManage.jsp");
    }
}
