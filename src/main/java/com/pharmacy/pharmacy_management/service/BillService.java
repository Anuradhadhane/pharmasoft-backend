package com.pharmacy.pharmacy_management.service;

import com.pharmacy.pharmacy_management.model.Bill;
import java.util.List;

public interface BillService {

    Bill addBill(Bill bill);

    List<Bill> getAllBills();

    Bill getBillById(Long id);

    Bill updateBill(Long id, Bill bill);

    void deleteBill(Long id);
}
