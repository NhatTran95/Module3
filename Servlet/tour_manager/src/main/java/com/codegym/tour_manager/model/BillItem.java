package com.codegym.tour_manager.model;

public class BillItem {
    private int id;
    private int idTour;
    private Tour tour;
    private int idCart;

    private float price;
    private int quantity;

    public BillItem(int id, int idTour, int idCart, float price, int quantity) {
        this.id = id;
        this.idTour = idTour;
        this.idCart = idCart;
        this.price = price;
        this.quantity = quantity;
    }

    public BillItem(int idTour, Tour tour, int idCart, float price, int quantity) {
        this.idTour = idTour;
        this.tour = tour;
        this.idCart = idCart;
        this.price = price;
        this.quantity = quantity;
    }

    public BillItem(int id, int idTour, Tour tour, int idCart, float price, int quantity) {
        this.id = id;
        this.idTour = idTour;
        this.tour = tour;
        this.idCart = idCart;
        this.price = price;
        this.quantity = quantity;
    }

    public BillItem() {
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

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
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
