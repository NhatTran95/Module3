package com.codegym.tour_manager.service;

import com.codegym.tour_manager.model.Cart;
import com.codegym.tour_manager.model.CartItem;
import com.codegym.tour_manager.model.Tour;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class CartService extends DbContext implements ICartService{
    private ITourService tourService = new TourServiceMyspl();
    private ICartItemService cartItemService = new CartItemService();
    @Override
    public Cart getCartById(int idUser) {
        Cart cart = null;
        Connection connection = getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(" SELECT * FROM carts where idUser = ?");

            ps.setInt(1, idUser);

            System.out.println("getCartById: " + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate date = rs.getDate("createAt").toLocalDate();
                double total = rs.getDouble("total");
                int idUserDB = rs.getInt("idUser");

                cart = new Cart(id, date, total, idUserDB);
                List<CartItem> cartItems = cartItemService.getAllCartItems(cart.getId());
                cart.setCartItems(cartItems);
                cart.updateTotal();
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return cart;
    }

    @Override
    public Cart createCart(Cart cart) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `carts` (`createAt`, `total`, `idUser`) VALUES (?, ?, ?);");
            ps.setDate(1, Date.valueOf(cart.getCreateAt()));
            ps.setDouble(2, cart.getTotal());
            ps.setInt(3, cart.getIdUser());
            ps.executeUpdate();

            System.out.println("createCart: " + ps);
            ps = connection.prepareStatement("SELECT LAST_INSERT_ID() as last_id;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maxId = rs.getInt("last_id");
                cart.setId(maxId);
            }
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

        return cart;
    }

    @Override
    public Cart updateCart(Cart cart) {
        try{
            Connection connection = getConnection();
            PreparedStatement pr = connection.prepareStatement("UPDATE `carts` SET `total` = ? WHERE `id` = ?");
            pr.setDouble(1,cart.getTotal());
            pr.setInt(2, cart.getId());

            System.out.println("updateCart:" + pr);
            pr.executeUpdate();
        }catch (SQLException e){
            printSQLException(e);
        }
        return null;
    }

    @Override
    public void addToCart(int idTour, int quantity, int idUser) {
        Tour tour = tourService.findById(idTour);

        Cart cart = getCartById(idUser);
        if (cart == null) {
            cart = new Cart(LocalDate.now(), 0, idUser);
            cart = createCart(cart);            // thằng cart ở đây là có id
        }
        CartItem cartItem = cartItemService.findCartItemById(cart.getId(), idTour);
        if (cartItem != null) {
            cartItem.setQuantity(quantity + cartItem.getQuantity());
            cartItemService.updateCartItem(cartItem);

        } else {
            CartItem cartItemCreate = new CartItem(idTour, tour.getPrice(), quantity);
            cartItemCreate.setIdCart(cart.getId());
            cartItemService.saveCartItem(cartItemCreate);
        }
    }

    @Override
    public Cart updateCartInfo(int idUser, int idTour, int quantity) {
        Cart cart = getCartById(idUser);
        CartItem cartItem = cartItemService.findCartItemById(cart.getId(), idTour);
        if (cartItem != null) {
            cartItem.setQuantity(quantity);
            cartItemService.updateCartItem(cartItem);
        }
        cart = getCartById(idUser);
        return cart;
    }

    @Override
    public void remove(int idCart) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM `carts` WHERE (`id` = ?);");
            ps.setInt(1, idCart);

            System.out.println("deleteCart: " + ps);
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }


}
