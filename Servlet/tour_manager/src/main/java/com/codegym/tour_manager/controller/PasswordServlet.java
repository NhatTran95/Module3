package com.codegym.tour_manager.controller;

import com.codegym.tour_manager.AppConfig.AppConfig;
import com.codegym.tour_manager.model.User;
import com.codegym.tour_manager.service.IUserService;
import com.codegym.tour_manager.service.UserService;
import com.codegym.tour_manager.utils.PasswordUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PasswordServlet", urlPatterns = "/password")
public class PasswordServlet extends HttpServlet {
    private IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action) {
            case "edit":
                RequestDispatcher rp = req.getRequestDispatcher(AppConfig.VIEW_DASHBOARD + "password/edit.jsp");
                rp.forward(req, resp);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();

        int id = Integer.parseInt(req.getParameter("id"));
        String password = req.getParameter("oldpass");
        User user = userService.findUserById(id);
        if (user != null && PasswordUtils.isValidPassword(password, user.getPassword())) {
//            req.getSession().setAttribute("user", user);
//            resp.sendRedirect("/home");
            String passnew = req.getParameter("newpass");
//            String strPass = PasswordUtils.hashPassword(passnew);
            user.setPassword(passnew);
            userService.updatePassword(id, user);
            req.setAttribute("messageEdit", "Sửa thành công");
            resp.sendRedirect("/user");

        }else {
            // thêm các message lỗi vào đây
            errors.add("Mật khẩu cũ không đúng");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher(AppConfig.VIEW_DASHBOARD + "password/edit.jsp").forward(req, resp);
        }
    }
}
