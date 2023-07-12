package com.codegym.manager_products.controller;

import com.codegym.manager_products.model.Category;
import com.codegym.manager_products.model.ESize;
import com.codegym.manager_products.model.Product;
import com.codegym.manager_products.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name ="ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private IProductService productService = new ProductServiceMysql();
    private ICategoryService categoryService = new CategoryServiceMysql();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreate(req, resp);
                break;                  // LUÔN NHỚ Có BREAK
            case "edit":
                showEdit(req, resp);
                break;
            case "delete":
                deleteProduct(req, resp);
                break;
            default:
                showList(req, resp);

        }
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       long idProduct = Long.parseLong(req.getParameter("id"));

        Product product = productService.findById(idProduct);
        req.setAttribute("product", product);

        List<Category> categories = categoryService.findAll();
        req.setAttribute("categories", categories);

        ESize[] sizes = ESize.values();
        req.setAttribute("sizes", sizes);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/products/edit.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        req.setAttribute("categories", categories);

        ESize[] sizes = ESize.values();
        req.setAttribute("sizes", sizes);

        List<Product> productList = productService.findAll();
        req.setAttribute("products", productList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/products/create.jsp");
        requestDispatcher.forward(req, resp);
    }
    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(req.getParameter("id"));
        productService.remove(id);

        req.getSession().setAttribute("messageDelete", "Xóa thành công");
        resp.sendRedirect("/products");
    }
    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = productService.findAll();
        req.setAttribute("products", productList);

        ESize[] sizes = ESize.values();
        req.setAttribute("sizes", sizes);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/products/list.jsp");
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
                saveProduct(req, resp);
                break;
            case "edit":
                updateProduct(req, resp);
                break;
        }
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String priceString = req.getParameter("price");
        BigDecimal price = new BigDecimal(priceString);
        String createAtStr = req.getParameter("createAt");
        LocalDate createAt = LocalDate.parse(createAtStr);

        LocalDateTime now = LocalDateTime.now();
        Instant updateAtI = now.atZone(ZoneId.systemDefault()).toInstant();

        Product product = productService.findById(id);
        product.setDescription(description);
        product.setName(name);
        product.setPrice(price);
        product.setCreateAt(createAt);
        product.setUpdateAt(updateAtI);

        int idSize = Integer.parseInt(req.getParameter("size"));
        ESize eSize = ESize.findById(idSize);
        product.setSize(eSize);

        int idCate = Integer.parseInt(req.getParameter("category"));
        Category category = categoryService.findById(idCate);
        product.setCategory(category);


        productService.update(id, product);
        req.setAttribute("updateAt", updateAtI);
        req.getSession().setAttribute("messageEdit", "Sửa thành công");
        resp.sendRedirect("/products");            // Dùng respone để sendRedirect


    }

    private void saveProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String priceString = req.getParameter("price");
        BigDecimal price = new BigDecimal(priceString);

        String createAtStr = req.getParameter("createAt");
        LocalDate createAt = LocalDate.parse(createAtStr);

        String updateAtStr = req.getParameter("updateAt");
        LocalDate date = LocalDate.parse(updateAtStr);
        LocalDateTime dateTime = date.atStartOfDay();
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneOffset.UTC);
        String updateAtStr1 = zonedDateTime.format(DateTimeFormatter.ISO_INSTANT);
        Instant updateAt = Instant.parse(updateAtStr1);


        long id = (long)(Math.random() * 10000);
        Product product = new Product(id, name, description, price, createAt, updateAt);

        int idSize = Integer.parseInt(req.getParameter("size"));
        ESize eSize = ESize.findById(idSize);
        product.setSize(eSize);

        int idCate = Integer.parseInt(req.getParameter("category"));
        Category category = categoryService.findById(idCate);
        product.setCategory(category);
        productService.save(product);

        ESize[] sizes = ESize.values();
        req.setAttribute("sizes", sizes);

        List<Category> categories = categoryService.findAll();
        req.setAttribute("categories", categories);

        req.setAttribute("message", "Thêm thành công");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/products/create.jsp");
        requestDispatcher.forward(req, resp);
    }
}
