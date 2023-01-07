package com.qxy.filter;

import com.qxy.constant.Constant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/1/5 2:59
 * @Introduction:
 */
@WebFilter("/*")
public class LoginFilter extends HttpFilter {
    private static final long serialVersionUID = 4198019322171681634L;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        List<String> whiteNames = Arrays.asList(
                req.getContextPath() + "/user/login",
                req.getContextPath() + "/index.jsp");
        if (whiteNames.contains(req.getRequestURI())) {
            chain.doFilter(req, res);
        } else {
            //判断登录状态,没有登录的则需要登录
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute(Constant.CURR_USER) == null) {
                res.sendRedirect(req.getContextPath() + "/index.jsp");
            } else {
                chain.doFilter(req, res);
            }
        }
    }
}
