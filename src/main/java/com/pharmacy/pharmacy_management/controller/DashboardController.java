package com.pharmacy.pharmacy_management.controller;

import com.pharmacy.pharmacy_management.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:3000")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/stats")
    public Map<String, Object> getDashboardStats() {
        return dashboardService.getStats();
    }

    @GetMapping("/alerts")
    public Map<String, Object> getAlerts() {
        return dashboardService.getAlerts();
    }
}
