package com.pharmacy.pharmacy_management.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.pharmacy_management.model.ExpiryAlert;
import com.pharmacy.pharmacy_management.model.Medicine;
import com.pharmacy.pharmacy_management.repository.ExpiryAlertRepository;
import com.pharmacy.pharmacy_management.repository.MedicineRepository;

@Service
public class ExpiryAlertServiceImpl implements ExpiryAlertService {

    @Autowired
    private ExpiryAlertRepository expiryAlertRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public ExpiryAlert createAlert(ExpiryAlert alert) {
        return expiryAlertRepository.save(alert);
    }

    @Override
    public List<ExpiryAlert> getAllAlerts() {
        return expiryAlertRepository.findAll();
    }

    @Override
    public ExpiryAlert getAlertById(Long id) {
        return expiryAlertRepository.findById(id).orElse(null);
    }

    @Override
    public ExpiryAlert updateAlert(Long id, ExpiryAlert alert) {
        ExpiryAlert existing = expiryAlertRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setMedicineId(alert.getMedicineId());
            existing.setMedicineName(alert.getMedicineName());
            existing.setExpiryDate(alert.getExpiryDate());
            existing.setAlertSent(alert.getAlertSent());
            return expiryAlertRepository.save(existing);
        }
        return null;
    }

    @Override
    public void deleteAlert(Long id) {
        expiryAlertRepository.deleteById(id);
    }

    // ================= EXPIRY LOGIC =================

    @Override
    public void generateExpiryAlerts() {
        LocalDate today = LocalDate.now();
        LocalDate next30 = today.plusDays(30);

        List<Medicine> medicines =
                medicineRepository.findByExpiryDateBetween(today.minusDays(1), next30);

        for (Medicine m : medicines) {
            if (!expiryAlertRepository.existsByMedicineId(m.getId())) {
                ExpiryAlert alert = new ExpiryAlert();
                alert.setMedicineId(m.getId());
                alert.setMedicineName(m.getName());
                alert.setExpiryDate(m.getExpiryDate());
                alert.setAlertSent(false);
                expiryAlertRepository.save(alert);
            }
        }
    }

    @Override
    public long getUnreadCount() {
        return expiryAlertRepository.countByAlertSentFalse();
    }

    @Override
    public void markAsRead(Long id) {
        ExpiryAlert alert = expiryAlertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found"));
        alert.setAlertSent(true);
        expiryAlertRepository.save(alert);
    }
}
