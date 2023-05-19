package com.example.demo.service;

import com.example.demo.entity.Customers;
import com.example.demo.repository.CustomersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomersService {
    @Autowired
    private final CustomersRepository customersRepository;

    public List<Customers> getListCustomer() {
        return  customersRepository.findAll();
    }

    public Customers getCustomerById(Long id) {
        return customersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + id));
    }

    public Customers createCustomer(Customers customers) {

        return customersRepository.save(customers);
    }

//    public Customers updateCustomerById(Long id, Customers customers) {
//        try {
//            Customers cekid = getCustomerById(id);
//            if (cekid != null) {
//
//            } else {
//                throw new EntityNotFoundException("Customer not found with ID: " + id);
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    public Customers updateCustomerById(Long id, Customers customers) {
        try {
            Optional<Customers> existingCustomerOptional = customersRepository.findById(id);
            if (existingCustomerOptional.isPresent()) {
                Customers existingCustomer = existingCustomerOptional.get();


                existingCustomer.setTotalPrice(customers.getTotalPrice());

                return customersRepository.save(existingCustomer);
            } else {
                throw new EntityNotFoundException("Customer not found with ID: " + id);
            }
        } catch (EntityNotFoundException e) {
            throw e; // Re-throw the EntityNotFoundException
        } catch (Exception e) {
            throw new RuntimeException("Failed to update customer with ID: " + id, e);
        }
    }

    public void deleteCustomer(Long id) {
        customersRepository.deleteById(id);
    }
}
