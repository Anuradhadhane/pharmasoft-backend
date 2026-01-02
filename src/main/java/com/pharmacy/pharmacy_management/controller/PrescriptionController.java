package com.pharmacy.pharmacy_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pharmacy.pharmacy_management.model.Prescription;
import com.pharmacy.pharmacy_management.service.PrescriptionService;

@RestController
@RequestMapping("/api/prescription")
@CrossOrigin(origins = "*")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    // 📤 Upload Prescription
    @PostMapping("/upload")
    public Prescription uploadPrescription(
            @RequestParam("patientName") String patientName,
            @RequestParam("doctorName") String doctorName,
            @RequestParam("file") MultipartFile file) {

        return prescriptionService.uploadPrescription(patientName, doctorName, file);
    }

    // 📃 Get All
    @GetMapping
    public List<Prescription> getAll() {
        return prescriptionService.getAllPrescriptions();
    }

    // 🔍 Get One
    @GetMapping("/{id}")
    public Prescription getById(@PathVariable Long id) {
        return prescriptionService.getPrescriptionById(id);
    }

    // 🗑 Delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        return "Prescription deleted successfully!";
    }
    
}
