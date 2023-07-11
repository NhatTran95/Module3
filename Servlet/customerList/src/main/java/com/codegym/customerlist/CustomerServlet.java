package com.codegym.customerlist;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    List<Customer> customers = new ArrayList<>();
    Customer c1 = new Customer("Maria Ozawa", "18-09-1890", "Nhật Bản", "https://www.ldg.com.vn/media/ar/ozawa.png");
    Customer c2 = new Customer("Tokuda", "20-12-1860", "Nhật Bản", "https://gamek.mediacdn.vn/133514250583805952/2020/5/16/photo-1-15896097558091974904037.png");
    Customer c3 = new Customer("Taylor Swift", "06-06-1980", "Mỹ", "https://images2.thanhnien.vn/thumb_w/686/528068263637045248/2023/6/6/4-1686019673557851132968-0-0-526-394-crop-16860196898721876545587.jpg");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);
        req.setAttribute("customers", customers);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req,resp);
    }
}
