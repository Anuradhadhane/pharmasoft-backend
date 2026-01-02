package com.pharmacy.pharmacy_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.pharmacy_management.model.ExpiryAlert;

@Repository
public interface ExpiryAlertRepository extends JpaRepository<ExpiryAlert, Long> {

    boolean existsByMedicineId(Long medicineId);

    long countByAlertSentFalse();
}
