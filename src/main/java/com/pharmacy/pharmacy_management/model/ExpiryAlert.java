package com.pharmacy.pharmacy_management.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ExpiryAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long medicineId;
    private String medicineName;
    private LocalDate expiryDate;

    private Boolean alertSent = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Boolean getAlertSent() {
		return alertSent;
	}

	public void setAlertSent(Boolean alertSent) {
		this.alertSent = alertSent;
	}

    // getters & setters
    
    
}
