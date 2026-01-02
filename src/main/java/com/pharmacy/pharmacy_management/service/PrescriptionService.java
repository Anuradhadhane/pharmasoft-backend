package com.pharmacy.pharmacy_management.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.pharmacy.pharmacy_management.model.Prescription;

public interface PrescriptionService {

    Prescription uploadPrescription(String patientName, String doctorName, MultipartFile file);

    List<Prescription> getAllPrescriptions();

    Prescription getPrescriptionById(Long id);

    void deletePrescription(Long id);
}
