package com.codegym.tour_manager.controller;

import com.codegym.tour_manager.AppConfig.AppConfig;
import com.codegym.tour_manager.model.*;
import com.codegym.tour_manager.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/checkout")
public class CheckOut extends HttpServlet {
    private ICartService cartService = new CartService();
    private ICartItemService cartItemService = new CartItemService();
    private IBillService billService = new BillService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        int id = Integer.parseInt(req.getParameter("id"));
//        System.out.println(234);

        //get cart + cart items

        //create bill

        //clear cart
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/login");
            return;
        }

        Cart cart = cartService.getCartById(user.getId());
        int idBill = cart.getId();
        int idUserBill = cart.getIdUser();
        double totalBill = cart.getTotal();
        LocalDate createAtBill = LocalDate.now();
        Bill bill = new Bill(idBill, createAtBill, totalBill, idUserBill);
        List<CartItem> cartItemList = cart.getCartItems();
        List<BillItem> cartItemsBill = new ArrayList<>();
        for(CartItem cartItem : cartItemList){
            int idCT = cartItem.getId();
            int idCart = cartItem.getIdCart();
            int idTour = cartItem.getIdTour();
            Tour tour = cartItem.getTour();
            int quantity = cartItem.getQuantity();
            float price = cartItem.getPrice();
            BillItem billItem = new BillItem(idCT,idTour,tour,idCart,price,quantity);
            cartItemsBill.add(billItem);
        }
        billService.createBill(bill);
        for(BillItem billItem : cartItemsBill){
            billService.createBillItem(billItem);
        }

        for(CartItem cartItem : cartItemList){
        cartItemService.deleteCartItem(cartItem.getId());}
        cartService.remove(cart.getId());

        resp.sendRedirect("/cart");



    }
}
