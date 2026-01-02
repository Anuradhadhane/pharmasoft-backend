package com.pharmacy.pharmacy_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pharmacy.pharmacy_management.model.ExpiryAlert;
import com.pharmacy.pharmacy_management.service.ExpiryAlertService;

@RestController
@RequestMapping("/api/expiry")
@CrossOrigin("*")
public class ExpiryAlertController {

    @Autowired
    private ExpiryAlertService service;

    @GetMapping("/generate")
    public void generateAlerts() {
        service.generateExpiryAlerts();
    }

    @GetMapping("/all")
    public List<ExpiryAlert> getAllAlerts() {
        return service.getAllAlerts();
    }

    @GetMapping("/count")
    public long getAlertCount() {
        return service.getUnreadCount();
    }

    @PutMapping("/read/{id}")
    public void markRead(@PathVariable Long id) {
        service.markAsRead(id);
    }
}
