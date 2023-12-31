package com.codegym.translate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "TranslateServlet", urlPatterns = "/translate")
public class TranslateServlet extends HttpServlet {
    private Map<String, String> dictionary;

    public TranslateServlet() {
        dictionary = new HashMap<>();
        dictionary.put("hello", "Xin chào");
        dictionary.put("yellow", "Màu vàng");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/translate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String kw = req.getParameter("kw");         // hello
        String result;   // xin chao
        if(dictionary.get(kw.toLowerCase()) == null){
            result = "Not found!!!";
        }else result = dictionary.get(kw.toLowerCase());
        req.setAttribute("result", result);         // nhét giá trị xin chào vào request theo tên thuộc tính là result
        req.setAttribute("keyword" , kw);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/translate.jsp");
        requestDispatcher.forward(req, resp);

    }
}
