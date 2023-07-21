package com.codegym.tour_manager.model;

public class CartItem {
    private int id;
    private int idTour;
    private Tour tour;
    private int idCart;

    private float price;
    private int quantity;


    public CartItem(int id, int idTour, int idCart, float price, int quantity) {
        this.id = id;
        this.idTour = idTour;
        this.idCart = idCart;
        this.price = price;
        this.quantity = quantity;
    }
    public CartItem(int idTour, int idCart, float price, int quantity) {
        this.idTour = idTour;
        this.idCart = idCart;
        this.price = price;
        this.quantity = quantity;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public CartItem(int idTour, float price, int quantity) {
        this.idTour  = idTour;
        this.price = price;
        this.quantity = quantity;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTour() {
        return idTour;
    }

    public void setIdTour(int idTour) {
        this.idTour = idTour;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
