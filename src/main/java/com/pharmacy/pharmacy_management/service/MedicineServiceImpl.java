package com.pharmacy.pharmacy_management.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.pharmacy_management.model.Medicine;
import com.pharmacy.pharmacy_management.repository.MedicineRepository;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Medicine addMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    @Override
    public Medicine updateMedicine(Long id, Medicine medicine) {
        Medicine existing = medicineRepository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setName(medicine.getName());
        existing.setBrand(medicine.getBrand());
        existing.setQuantity(medicine.getQuantity());
        existing.setPrice(medicine.getPrice());
        existing.setExpiryDate(medicine.getExpiryDate());

        return medicineRepository.save(existing);
    }

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    @Override
    public Medicine getMedicineById(Long id) {
        return medicineRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }

    @Override
    public List<Medicine> getExpiredMedicines() {
        LocalDate today = LocalDate.now();
        return medicineRepository.findByExpiryDateBefore(today);
    }
}
