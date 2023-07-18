package com.codegym.tour_manager.controller;

import com.codegym.tour_manager.AppConfig.AppConfig;
import com.codegym.tour_manager.model.Pageable;
import com.codegym.tour_manager.model.Tour;
import com.codegym.tour_manager.service.ITourService;
import com.codegym.tour_manager.service.TourServiceMyspl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    private ITourService tourService = new TourServiceMyspl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Tour> tours = tourService.findAll();


        Pageable pageable = new Pageable();

        readPageable(req, pageable);
        List<Tour> tours =tourService.findTour(pageable);
        req.setAttribute("tours", tours);
        req.setAttribute("pageable", pageable);




        RequestDispatcher requestDispatcher = req.getRequestDispatcher(AppConfig.VIEW_FRONTEND + "index.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void readPageable(HttpServletRequest req, Pageable pageable) {
        String kw = "";
        if(req.getParameter("kw") != null){
            kw = req.getParameter("kw");
        }
        pageable.setKw(kw);
        int page = 1;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));

        }
        pageable.setPage(page);

        int limit = 3;
        if (req.getParameter("limit") != null) {
            limit = Integer.parseInt(req.getParameter("limit"));

        }
        pageable.setLimit(limit);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
