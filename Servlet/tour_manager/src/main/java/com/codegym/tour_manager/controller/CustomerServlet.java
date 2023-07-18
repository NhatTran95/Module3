package com.codegym.tour_manager.controller;

import com.codegym.tour_manager.AppConfig.AppConfig;
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
import java.net.HttpRetryException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    private IUserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.findRoleUser();
        req.setAttribute("users", users);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(AppConfig.VIEW_DASHBOARD + "customer/customer.jsp");
        requestDispatcher.forward(req, resp);
    }
}
