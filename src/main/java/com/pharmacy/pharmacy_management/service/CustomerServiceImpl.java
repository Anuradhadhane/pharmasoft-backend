package com.pharmacy.pharmacy_management.service;

import com.pharmacy.pharmacy_management.model.Customer;
import com.pharmacy.pharmacy_management.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repo;

    @Override
    public Customer addCustomer(Customer customer) {
        return repo.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer existing = repo.findById(id).orElse(null);

        if (existing != null) {
            existing.setName(customer.getName());
            existing.setPhone(customer.getPhone());
            existing.setEmail(customer.getEmail());
            return repo.save(existing);
        }

        return null;
    }

    @Override
    public void deleteCustomer(Long id) {
        repo.deleteById(id);
    }
}
