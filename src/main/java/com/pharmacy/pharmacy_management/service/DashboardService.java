package com.pharmacy.pharmacy_management.service;

import java.util.Map;

public interface DashboardService {
    Map<String, Object> getStats();
    Map<String, Object> getAlerts();
}
