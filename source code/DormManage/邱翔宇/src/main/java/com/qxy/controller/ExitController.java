package com.qxy.controller;

import com.qxy.constant.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * @Author: SayHello
 * @Date: 2023/1/5 3:16
 * @Introduction:
 */
@WebServlet("/user/exit")
public class ExitController extends HttpServlet {
    private static final long serialVersionUID = -2236230463209246651L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //修改成功清除session,重新登录
        HttpSession session = req.getSession(false);
        session.removeAttribute(Constant.CURR_USER);
        session.invalidate();
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
