package com.codegym.tour_manager.controller;

import com.codegym.tour_manager.AppConfig.AppConfig;
import com.codegym.tour_manager.model.ERole;
import com.codegym.tour_manager.model.User;
import com.codegym.tour_manager.service.IUserService;
import com.codegym.tour_manager.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private IUserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(AppConfig.VIEW_FRONTEND + "register.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (userService.findUserByUserName(username) == null) {
            User user = new User(username, password);
            user.setRole(ERole.USER);
            userService.createUser(user);

            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/home");
        }else{
            // báo lỗi đã tồn tại
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(AppConfig.VIEW_FRONTEND + "register.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
