package com.codegym.tour_manager.service;

import com.codegym.tour_manager.model.Bill;
import com.codegym.tour_manager.model.BillItem;

public interface IBillService {
    Bill createBill(Bill bill);
    BillItem createBillItem(BillItem bill);
}
