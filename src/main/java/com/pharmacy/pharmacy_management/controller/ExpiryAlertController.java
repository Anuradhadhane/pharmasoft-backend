package com.pharmacy.pharmacy_management.controller;

import com.pharmacy.pharmacy_management.model.Medicine;
import com.pharmacy.pharmacy_management.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expiry")
@CrossOrigin(origins = "http://localhost:3000")
public class ExpiryAlertController {

    @Autowired
    private MedicineRepository medicineRepository;

    // 🔹 Fetch unread expiry alerts
    @GetMapping("/alerts")
    public List<Medicine> getExpiryAlerts() {
        return medicineRepository
                .findByExpiryDateBeforeAndExpiryReadFalse(LocalDate.now());
    }

    // 🔹 Mark alert as read
    @PutMapping("/mark-read/{id}")
    public void markAsRead(@PathVariable Long id) {
        Medicine med = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        med.setExpiryRead(true);   // ✅ FIXED
        medicineRepository.save(med);
    }
}
