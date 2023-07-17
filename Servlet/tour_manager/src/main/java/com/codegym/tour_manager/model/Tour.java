package com.codegym.tour_manager.model;

import java.time.LocalDate;
import java.util.Date;

public class Tour {
    private int id;
    private String destination;
    private String route;
    private LocalDate startDate;
    private LocalDate endDate;
    private float price;
    private String img;

    public Tour(int id, String destination, String route, LocalDate startDate, LocalDate endDate, float price, String img) {
        this.id = id;
        this.destination = destination;
        this.route = route;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.img = img;
    }

    public Tour() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
