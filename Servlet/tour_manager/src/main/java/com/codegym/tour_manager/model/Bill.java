package com.codegym.tour_manager.model;

import java.time.LocalDate;
import java.util.List;

public class Bill {
    private int id;
    private LocalDate createAt;
    List<BillItem> billItems;

    private double total;

    private int idUser;

    public Bill(int id, LocalDate createAt, double total, int idUser) {
        this.id = id;
        this.createAt = createAt;
        this.total = total;
        this.idUser = idUser;
    }

    public Bill(int id, LocalDate createAt, List<BillItem> billItems, double total, int idUser) {
        this.id = id;
        this.createAt = createAt;
        this.billItems = billItems;
        this.total = total;
        this.idUser = idUser;
    }

    public Bill() {
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

    public List<BillItem> getBillItems() {
        return billItems;
    }

    public void setBillItems(List<BillItem> billItems) {
        this.billItems = billItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
