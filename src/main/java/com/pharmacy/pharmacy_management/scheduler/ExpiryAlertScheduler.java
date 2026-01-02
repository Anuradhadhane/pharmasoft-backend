package com.pharmacy.pharmacy_management.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pharmacy.pharmacy_management.service.ExpiryAlertService;

@Component
public class ExpiryAlertScheduler {

    @Autowired
    private ExpiryAlertService expiryAlertService;

    // Runs every day at 9 AM
    @Scheduled(cron = "0 0 9 * * ?")
    public void runDailyExpiryCheck() {
        expiryAlertService.generateExpiryAlerts();
        System.out.println("✅ Daily expiry alert check completed");
    }
}
