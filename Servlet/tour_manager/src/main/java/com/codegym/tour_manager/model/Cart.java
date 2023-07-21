package com.codegym.tour_manager.model;

import java.time.LocalDate;
import java.util.List;

public class Cart {
    private int id;
    private LocalDate createAt;
    List<CartItem> cartItems;

    private double total;

    private int idUser;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Cart(int id, LocalDate createAt, double total, int idUser) {
        this.id = id;
        this.createAt = createAt;
        this.total = total;
        this.idUser = idUser;
    }
    public Cart(LocalDate createAt, double total, int idUser) {
        this.createAt = createAt;
        this.total = total;
        this.idUser = idUser;
    }

    public Cart(int id, LocalDate createAt, List<CartItem> cartItems, double total) {
        this.id = id;
        this.createAt = createAt;
        this.cartItems = cartItems;
        this.total = total;
    }

    public Cart(int id, LocalDate createAt, double total) {
        this.id = id;
        this.createAt = createAt;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    public void updateTotal() {
        double total = 0;
        if (cartItems != null) {
            for (int i = 0; i < cartItems.size(); i++) {
                total += cartItems.get(i).getQuantity() * cartItems.get(i).getPrice();
            }
        }
        this.total = total;
    }

}
