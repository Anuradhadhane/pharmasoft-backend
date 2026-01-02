package com.pharmacy.pharmacy_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.pharmacy_management.model.Bill;
import com.pharmacy.pharmacy_management.model.BillItem;
import com.pharmacy.pharmacy_management.model.Customer;
import com.pharmacy.pharmacy_management.model.Medicine;
import com.pharmacy.pharmacy_management.repository.BillRepository;
import com.pharmacy.pharmacy_management.repository.CustomerRepository;
import com.pharmacy.pharmacy_management.repository.MedicineRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Bill addBill(Bill bill) {

        // 🔹 CUSTOMER (SAFE)
        Customer customer = customerRepository
                .findByPhone(bill.getCustomer().getPhone())
                .orElseGet(() -> customerRepository.save(bill.getCustomer()));

        bill.setCustomer(customer);

        double total = 0;

        for (BillItem item : bill.getMedicines()) {

            Medicine med = medicineRepository.findById(item.getMedicineId())
                    .orElseThrow(() -> new RuntimeException("Medicine not found"));

            if (med.getQuantity() < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock");
            }

            item.setMedicineName(med.getName());
            item.setPrice(med.getPrice());

            double amount = med.getPrice() * item.getQuantity();
            item.setAmount(amount);
            total += amount;

            med.setQuantity(med.getQuantity() - item.getQuantity());
            medicineRepository.save(med);
        }

        bill.setTotalAmount(total);

        return billRepository.save(bill);
    }

    @Override
    public Bill getBillById(Long id) {
        return billRepository.findById(id).orElse(null);
    }


    @Override
    public Bill updateBill(Long id, Bill bill) {
        Bill existingBill = billRepository.findById(id).orElse(null);

        if (existingBill != null) {
            existingBill.setCustomer(bill.getCustomer());
            existingBill.setDate(bill.getDate());
            existingBill.setMedicines(bill.getMedicines());
            existingBill.setTotalAmount(bill.getTotalAmount());
            return billRepository.save(existingBill);
        }
        return null;
    }

    @Override
    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }

	@Override
	public List<Bill> getAllBills() {
		// TODO Auto-generated method stub
		return null;
	}
}
