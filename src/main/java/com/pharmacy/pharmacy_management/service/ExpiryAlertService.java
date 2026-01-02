package com.pharmacy.pharmacy_management.service;

import java.util.List;
import com.pharmacy.pharmacy_management.model.ExpiryAlert;

public interface ExpiryAlertService {

    ExpiryAlert createAlert(ExpiryAlert alert);

    List<ExpiryAlert> getAllAlerts();

    ExpiryAlert getAlertById(Long id);

    ExpiryAlert updateAlert(Long id, ExpiryAlert alert);

    void deleteAlert(Long id);

    // expiry alert logic
    void generateExpiryAlerts();

    long getUnreadCount();

    void markAsRead(Long id);
}
