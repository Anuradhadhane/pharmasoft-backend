package com.pharmacy.pharmacy_management.service;

import java.util.List;
import com.pharmacy.pharmacy_management.model.Medicine;

public interface MedicineService {

    Medicine addMedicine(Medicine medicine);

    Medicine updateMedicine(Long id, Medicine medicine);

    List<Medicine> getAllMedicines();

    Medicine getMedicineById(Long id);

    void deleteMedicine(Long id);

    List<Medicine> getExpiredMedicines();
}
