package com.codegym.tour_manager.controller;

import com.codegym.tour_manager.AppConfig.AppConfig;
import com.codegym.tour_manager.model.ERole;
import com.codegym.tour_manager.model.Tour;
import com.codegym.tour_manager.model.User;
import com.codegym.tour_manager.service.IUserService;
import com.codegym.tour_manager.service.UserService;
import com.codegym.tour_manager.utils.ValidatesUtils;
import com.sun.glass.ui.Accessible;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    private IUserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        boolean isAdmin = user != null && user.getRole() == ERole.ADMIN;
        if (!isAdmin) {
            resp.sendRedirect("/login");
            return;
        }

        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action) {
            case "edit":
                RequestDispatcher requestDispatcher = req.getRequestDispatcher(AppConfig.VIEW_DASHBOARD + "Admin/edit.jsp");
                requestDispatcher.forward(req, resp);
                break;
            default:
                RequestDispatcher rp = req.getRequestDispatcher(AppConfig.VIEW_DASHBOARD + "Admin/info.jsp");
                rp.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> errors = new ArrayList<>();
        User user = new User();


        validateId(req, user, errors);
        validatefullname(req, user, errors);
        validatePhone(req, user, errors);
        validateEmail(req, user, errors);

//        int id = Integer.parseInt(req.getParameter("id"));
//        String fullname = req.getParameter("fullname");
//        String email = req.getParameter("email");
//        String phone = req.getParameter("phone");
        String username = req.getParameter("username");
        user.setUsername(username);
//        user.setFullname(fullname);
//        user.setEmail(email);
//        user.setPhone(phone);
//        userService.updateUser(user.getId(), user);
//        req.setAttribute("messageEditNew", "Sửa thành công");
//        req.getSession().setAttribute("user", user);
//        RequestDispatcher rp = req.getRequestDispatcher(AppConfig.VIEW_DASHBOARD + "Admin/info.jsp");
//        rp.forward(req, resp);

        if(!errors.isEmpty()){
            req.setAttribute("errors", errors);
            req.getSession().setAttribute("user", user);
            RequestDispatcher rp = req.getRequestDispatcher(AppConfig.VIEW_DASHBOARD + "Admin/edit.jsp");
            rp.forward(req, resp);
        }
        else {
            userService.updateUser(user.getId(), user);
            req.getSession().setAttribute("user", user);
            req.setAttribute("messageEditNew", "Sửa thành công");
            RequestDispatcher rp = req.getRequestDispatcher(AppConfig.VIEW_DASHBOARD + "Admin/info.jsp");
            rp.forward(req, resp);
        }

    }
    private void validateEmail(HttpServletRequest req, User user, List<String> errors) {
        String email = req.getParameter("email");
        if(!ValidatesUtils.isEmailValid(email)){
            errors.add("Email không hợp lệ, theo dạng xxx@xx.xx");
        }
        user.setEmail(email);
    }

    private void validatePhone(HttpServletRequest req, User user, List<String> errors) {
        try{
            String phone = req.getParameter("phone");
            if (!ValidatesUtils.isPhoneValid(phone)) {
                errors.add("Số điện thoại không hợp lệ. Gồm 10 số bắt đầu là 0");
            }


            user.setPhone(phone);
        }catch (NumberFormatException n){
            errors.add("Định dạng số điện thoại không hợp lệ");
        }
    }

    private void validatefullname(HttpServletRequest req, User user, List<String> errors) {
        String fullname = req.getParameter("fullname");
        if(!ValidatesUtils.isFullnameValid(fullname)){
            errors.add("Tên không hợp lệ, viết hoa chữ cái đầu tiên mỗi từ");
        }
        user.setFullname(fullname);;
    }

    private void validateId(HttpServletRequest req, User user, List<String> errors) {
        try{
            int id = Integer.parseInt(req.getParameter("id"));
            if(userService.findUserById(id) == null){
                errors.add("Mã tour không hợp lệ");
            }
            user.setId(id);
        }catch (NumberFormatException n){
            errors.add("Định dạng mã tour không hợp lệ");
        }
    }
}
