package com.qxy.controller.dormmanager;

import com.qxy.dao.CommonDao;
import com.qxy.entity.DormBuild;
import com.qxy.service.CommonService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 10:28
 * @Introduction: 用于请求转发到宿舍管理员的主界面
 */
@WebServlet("/user/dormManager/main")
public class ForwardToMain extends HttpServlet {
    private static final long serialVersionUID = 1983795005483586580L;
    CommonService cm = new CommonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //请求转发到宿舍管理员-主界面
        req.getRequestDispatcher("/jsp/dormManager/dormManagerMain.jsp").forward(req, resp);
    }
}
