package com.codegym.tour_manager.controller;

import com.codegym.tour_manager.AppConfig.AppConfig;
import com.codegym.tour_manager.model.User;
import com.codegym.tour_manager.service.IUserService;
import com.codegym.tour_manager.service.UserService;
import com.codegym.tour_manager.utils.PasswordUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")

public class LoginServlet extends HttpServlet {
    private IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(AppConfig.VIEW_FRONTEND + "login.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.findUserByUserName(username);
        if (user != null && PasswordUtils.isValidPassword(password, user.getPassword())) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/home");

        }else {
            // thêm các message lỗi vào đây
            req.getRequestDispatcher(AppConfig.VIEW_FRONTEND + "login.jsp").forward(req, resp);
        }

    }
}
