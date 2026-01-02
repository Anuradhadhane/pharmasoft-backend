package com.pharmacy.pharmacy_management.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pharmacy.pharmacy_management.model.Medicine;
import com.pharmacy.pharmacy_management.model.ExpiryAlert;
import com.pharmacy.pharmacy_management.repository.MedicineRepository;
import com.pharmacy.pharmacy_management.repository.ExpiryAlertRepository;

@Component
public class ExpiryScheduler {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private ExpiryAlertRepository expiryAlertRepository;

    // Runs daily at 8:00 AM
    @Scheduled(cron = "0 0 8 * * ?")
    public void checkExpiry() {

        List<Medicine> medicines = medicineRepository.findAll();
        LocalDate today = LocalDate.now();

        for (Medicine med : medicines) {

            // if expiry date < (today + 30 days)
            if (med.getExpiryDate().isBefore(today.plusDays(30))) {

                // Create alert entry
                ExpiryAlert alert = new ExpiryAlert();
                alert.setMedicineId(med.getId());
                alert.setMedicineName(med.getName());
                alert.setExpiryDate(med.getExpiryDate());
                alert.setAlertSent(false);

                expiryAlertRepository.save(alert);
            }
        }

        System.out.println("🔔 Expiry check completed!");
    }
}
