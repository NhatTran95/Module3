package com.codegym.tour_manager.controller;

import com.codegym.tour_manager.AppConfig.AppConfig;
import com.codegym.tour_manager.model.ERole;
import com.codegym.tour_manager.model.Tour;
import com.codegym.tour_manager.model.User;
import com.codegym.tour_manager.service.ITourService;
import com.codegym.tour_manager.service.TourServiceMyspl;
import com.codegym.tour_manager.utils.ValidatesUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TourServlet", urlPatterns = "/tours")
public class TourServlet extends HttpServlet {
    private ITourService tourService = new TourServiceMyspl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        boolean isAdmin = user != null && user.getRole() == ERole.ADMIN;
        if (!isAdmin) {
            resp.sendRedirect("/login");
            return;
        }


        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreate(req, resp);
                break;
            case "edit":
                showEdit(req, resp);
                break;
            case "delete":
                deleteTour(req, resp);
                break;
            default:
                showList(req, resp);

        }
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tour> tours = tourService.findAll();
        req.setAttribute("tours", tours);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(AppConfig.VIEW_DASHBOARD + "tour/list.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void deleteTour(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        tourService.remove(id);

        req.getSession().setAttribute("messageDelete", "Xóa thành công");
        resp.sendRedirect("/tours");
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idTour = Integer.parseInt(req.getParameter("id"));
        Tour tour = tourService.findById(idTour);
        req.setAttribute("tour", tour);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(AppConfig.VIEW_DASHBOARD + "tour/edit.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tour> tours = tourService.findAll();
        req.setAttribute("tours", tours);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(AppConfig.VIEW_DASHBOARD +"tour/create.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                saveTour(req, resp);
                break;
            case "edit":
                updateTour(req, resp);
                break;
        }
    }

    private void updateTour(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Tour tour = new Tour();
        List<String> errors = new ArrayList<>();

        validateId(req, tour, errors);
        validateDestination(req, tour, errors);
        validateRoute(req, tour, errors);
        validatePrice(req, tour, errors);

        String startDateStr = req.getParameter("startDate");
        LocalDate startDate = LocalDate.parse(startDateStr);
        String endDateStr = req.getParameter("endDate");
        LocalDate endDate = LocalDate.parse(endDateStr);
        String img = req.getParameter("img");
        tour.setImg("\\frontend\\assets\\image\\" + img);
        tour.setStartDate(startDate);
        tour.setEndDate(endDate);

        if(!errors.isEmpty()){
            req.setAttribute("errors", errors);
            req.setAttribute("tour", tour);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(AppConfig.VIEW_DASHBOARD + "tour/edit.jsp");
            requestDispatcher.forward(req, resp);
        }
        else {
            tourService.update(tour.getId(), tour);
            req.getSession().setAttribute("messageEdit", "Sửa thành công");
            resp.sendRedirect("/tours");            // Dùng respone để sendRedirect
        }

    }

    private void validatePrice(HttpServletRequest req, Tour tour, List<String> errors) {
        try{
            String priceString = req.getParameter("price");
            if (!ValidatesUtils.isPriceValid(priceString)) {
                errors.add("Giá không hợp lệ, là số bắt đầu khác 0 và phải có từ 5-9 kí tự số");
            }

            float price = Float.parseFloat(priceString);
            tour.setPrice(price);
        }catch (NumberFormatException n){
            errors.add("Định dạng giá không hợp lệ");
        }
    }

    private void validateRoute(HttpServletRequest req, Tour tour, List<String> errors) {
        String route = req.getParameter("route");
        if(!ValidatesUtils.isDesValid(route)){
            errors.add("Lộ trình không hợp lệ, bắt đầu bằng chữ cái và phải có từ 15-100 kí tự");

        }
        tour.setRoute(route);
    }

    private void validateDestination(HttpServletRequest req, Tour tour, List<String> errors) {
        String destination = req.getParameter("destination");
        if(!ValidatesUtils.isNameValid(destination)){
            errors.add("Tên địa điểm không hợp lệ, bắt đầu bằng chữ cái và phải có từ 3-15 kí tự");
        }
        tour.setDestination(destination);
    }

    private void validateId(HttpServletRequest req, Tour tour, List<String> errors) {
        try{
            int id = Integer.parseInt(req.getParameter("id"));
            if(tourService.findById(id) == null){
                errors.add("Mã tour không hợp lệ");
            }
            tour.setId(id);
        }catch (NumberFormatException n){
            errors.add("Định dạng mã tour không hợp lệ");
        }
    }

    private void saveTour(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Tour tour = new Tour();
        List<String> errors = new ArrayList<>();

//        validateId(req, tour, errors);
        validateDestination(req, tour, errors);
        validateRoute(req, tour, errors);
        validatePrice(req, tour, errors);

        String startDateStr = req.getParameter("startDate");
        LocalDate startDate = LocalDate.parse(startDateStr);
        String endDateStr = req.getParameter("endDate");
        LocalDate endDate = LocalDate.parse(endDateStr);
        String img = req.getParameter("img");
        tour.setImg("\\frontend\\assets\\image\\"+img);
        tour.setStartDate(startDate);
        tour.setEndDate(endDate);

        if(!errors.isEmpty()){
            req.setAttribute("errors", errors);
            req.setAttribute("tour", tour);
        }else {
            req.setAttribute("message", "Thêm thành công");
            tourService.save(tour);
        }


        RequestDispatcher requestDispatcher = req.getRequestDispatcher(AppConfig.VIEW_DASHBOARD + "tour/create.jsp");
        requestDispatcher.forward(req, resp);
    }
}
