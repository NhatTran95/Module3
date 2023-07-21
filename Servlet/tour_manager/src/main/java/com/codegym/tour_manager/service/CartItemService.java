package com.codegym.tour_manager.service;

import com.codegym.tour_manager.model.CartItem;
import com.codegym.tour_manager.model.Tour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemService extends DbContext implements ICartItemService{
    private ITourService tourService = new TourServiceMyspl();
    @Override
    public List<CartItem> getAllCartItems(int idCart) {
        List<CartItem> cartItems = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM cart_items where idCart = ?; ");
            ps.setInt(1, idCart);
            System.out.println("getAllCartItems: " + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idTour = rs.getInt("idTour");
                int idCartDB = rs.getInt("idCart");
                int quantity = rs.getInt("quantity");
                Float price = rs.getFloat("price");

                Tour tour = tourService.findById(idTour);

                CartItem cartItem = new CartItem(id, idTour, idCartDB, price, quantity);
                cartItem.setTour(tour);
                cartItems.add(cartItem);

            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return cartItems;
    }

    @Override
    public CartItem saveCartItem(CartItem cartItem) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `cart_items` (`idTour`, `idCart`, `quantity`, `price`) " +
                    "VALUES (?, ?, ?, ?);");
            ps.setInt(1, cartItem.getIdTour());
            ps.setInt(2, cartItem.getIdCart());
            ps.setInt(3, cartItem.getQuantity());
            ps.setFloat(4, cartItem.getPrice());

            System.out.println("saveCartItem" + ps);
            ps.executeUpdate();

            ps = connection.prepareStatement("SELECT LAST_INSERT_ID() as last_id;");
            System.out.println("saveCartItem: " + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int lastId = rs.getInt("last_id");
                cartItem.setId(lastId);
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return cartItem;
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE `cart_items` SET `quantity` = ? WHERE (`id` = ?);");
            ps.setInt(1, cartItem.getQuantity());
            ps.setInt(2, cartItem.getId());
            ps.executeUpdate();

            ps = connection.prepareStatement("SELECT * FROM cart_items where id = ?");
            ps.setInt(1, cartItem.getId());

            System.out.println("updateCartItem: " + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idTour = rs.getInt("idTour");
                int idCartDB = rs.getInt("idCart");
                int quantity = rs.getInt("quantity");
                Float price = rs.getFloat("price");

                CartItem cartItemUpdate = new CartItem(id, idTour, idCartDB, price, quantity);
                return cartItemUpdate;
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }

    @Override
    public void deleteCartItem(int idCartItem) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM `cart_items` WHERE (`id` = ?);");
            ps.setInt(1, idCartItem);

            System.out.println("deleteCartItem: " + ps);
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    @Override
    public CartItem findCartItemById(int cartId, int idTour) {
        CartItem cartItem = null;
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `cart_items` where idCart = ? and idTour = ?");
            ps.setInt(1, cartId);
            ps.setInt(2, idTour);
            System.out.println("findCartItemById: " + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idTourDB = rs.getInt("idTour");
                int idCartDB = rs.getInt("idCart");
                int quantity = rs.getInt("quantity");
                Float price = rs.getFloat("price");
//

                cartItem = new CartItem(id, idTourDB, idCartDB, price, quantity);
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return cartItem;
    }
}
