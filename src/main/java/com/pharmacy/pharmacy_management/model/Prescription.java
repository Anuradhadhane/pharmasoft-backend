package com.pharmacy.pharmacy_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Prescription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Prescribeid;
	private String PatientName;
	private String DoctorName;
	private String prescriptionFileUrl;
	public long getPrescribeid() {
		return Prescribeid;
	}
	public void setPrescribeid(long prescribeid) {
		Prescribeid = prescribeid;
	}
	public String getPatientName() {
		return PatientName;
	}
	public void setPatientName(String patientName) {
		PatientName = patientName;
	}
	public String getDoctorName() {
		return DoctorName;
	}
	public void setDoctorName(String doctorName) {
		DoctorName = doctorName;
	}
	public String getPrescriptionFileUrl() {
		return prescriptionFileUrl;
	}
	public void setPrescriptionFileUrl(String prescriptionFileUrl) {
		this.prescriptionFileUrl = prescriptionFileUrl;
	}

	
}
