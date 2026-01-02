package com.pharmacy.pharmacy_management.controller;

import com.pharmacy.pharmacy_management.model.Bill;
import com.pharmacy.pharmacy_management.service.BillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins = "http://localhost:3000")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/save")
    public ResponseEntity<Bill> saveBill(@RequestBody Bill bill) {
        return ResponseEntity.ok(billService.addBill(bill));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBill(@PathVariable Long id) {
        return ResponseEntity.ok(billService.getBillById(id));
    }
}
