package com.codegym.tour_manager.service;

import com.codegym.tour_manager.model.Bill;
import com.codegym.tour_manager.model.BillItem;

import java.sql.*;

public class BillService extends DbContext implements IBillService {
    public Bill createBill(Bill bill) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `bills` ( `id`, `createAt`, `total`, `idUser`) VALUES (?, ?, ?, ?);");
            ps.setInt(1,bill.getId());
            ps.setDate(2, Date.valueOf(bill.getCreateAt()));
            ps.setDouble(3, bill.getTotal());
            ps.setInt(4, bill.getIdUser());
            ps.executeUpdate();

            System.out.println("createBill: " + ps);
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

        return bill;
    }

    public BillItem createBillItem(BillItem bill) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `bill_items` ( `id`, `idBill`, `idTour`, `quantity`, `price`) VALUES (?, ?, ?, ?, ?);");
            ps.setInt(1,bill.getId());
            ps.setInt(2,bill.getIdCart());
            ps.setInt(3,bill.getIdTour());
            ps.setInt(4,bill.getQuantity());
            ps.setFloat(5,bill.getPrice());

            ps.executeUpdate();

            System.out.println("createBill: " + ps);
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

        return bill;
    }
}
