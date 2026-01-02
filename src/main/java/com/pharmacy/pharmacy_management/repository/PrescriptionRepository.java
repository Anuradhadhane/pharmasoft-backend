package com.pharmacy.pharmacy_management.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.pharmacy_management.model.Prescription;

@Repository
@Qualifier("PrescribeRepo")
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

}
