package com.pharmacy.pharmacy_management.service;

import java.util.List;
import com.pharmacy.pharmacy_management.model.Supplier;

public interface SupplierService {

    Supplier addSupplier(Supplier supplier);

    Supplier updateSupplier(Long id, Supplier supplier);

    List<Supplier> getAllSuppliers();

    Supplier getSupplierById(Long id);

    void deleteSupplier(Long id);
}
