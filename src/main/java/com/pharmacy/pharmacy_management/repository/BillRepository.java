package com.pharmacy.pharmacy_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.pharmacy_management.model.Bill;


public interface BillRepository extends JpaRepository<Bill, Long> {

	List<Bill> findTop5ByOrderByIdDesc();


}
