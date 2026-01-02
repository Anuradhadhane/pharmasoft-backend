package com.pharmacy.pharmacy_management.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pharmacy.pharmacy_management.model.Prescription;
import com.pharmacy.pharmacy_management.repository.PrescriptionRepository;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final String UPLOAD_DIRECTORY = "uploads/";

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public Prescription uploadPrescription(String patientName, String doctorName, MultipartFile file) {

        try {
            // Create upload folder if not exists
            File folder = new File(UPLOAD_DIRECTORY);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Generate unique file name
            String uniqueName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIRECTORY + uniqueName);

            // Save file to disk
            Files.write(filePath, file.getBytes());

            // Save details to DB
            Prescription p = new Prescription();
            p.setPatientName(patientName);
            p.setDoctorName(doctorName);
            p.setPrescriptionFileUrl(filePath.toString());

            return prescriptionRepository.save(p);

        } catch (IOException e) {
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }
    }

    @Override
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    @Override
    public Prescription getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }
}
