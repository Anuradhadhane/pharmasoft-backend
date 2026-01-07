package com.pharmacy.pharmacy_management.service;

import com.pharmacy.pharmacy_management.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private BillRepository billRepository;

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> data = new HashMap<>();

        data.put("totalMedicines", medicineRepository.count());
        data.put("expiredMedicines",
                medicineRepository.countByExpiryDateBefore(LocalDate.now()));
        data.put("customers", customerRepository.count());
        data.put("suppliers", supplierRepository.count());

        return data;
    }

    @Override
    public Map<String, Object> getAlerts() {
        Map<String, Object> data = new HashMap<>();

        data.put("expiryAlerts",
                medicineRepository.findTop5ByExpiryDateAfterOrderByExpiryDateAsc(LocalDate.now()));

        data.put("recentActivity",
                billRepository.findTop5ByOrderByIdDesc());

        return data;
    }
}
