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
 * @Date: 2023/1/5 2:09
 * @Introduction:
 */
@WebServlet("/user/admin/record/delete")
public class DeleteRecordController extends HttpServlet {
    private static final long serialVersionUID = 4180643490882282341L;
    SuperManagerService sms = new SuperManagerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        sms.deleteRecordById(id);
        resp.sendRedirect(req.getContextPath() + "/jsp/admin/recordManage.jsp");
    }
}
