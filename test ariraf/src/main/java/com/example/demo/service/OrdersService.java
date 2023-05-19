package com.example.demo.service;

import com.example.demo.entity.Customers;
import com.example.demo.entity.Orders;
import com.example.demo.repository.OrdersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrdersService {

    @Autowired
    private  final OrdersRepository ordersRepository;

    public List<Orders> getLisOrder() {
        try {
            return  ordersRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Orders getOrderById(Long id) {
        try {
            return  ordersRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Orders createOrder(Orders orders) {
        try {

            return ordersRepository.save(orders);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Orders updateOrderById(Long id, Orders orders) {
        try {
            Optional<Orders> existingOrdersOptional = ordersRepository.findById(id);

            if (existingOrdersOptional.isPresent()) {
                Orders existingOrder = existingOrdersOptional.get();


                existingOrder.setEmail(orders.getEmail());
                existingOrder.setName(orders.getName());
                existingOrder.setPhone(orders.getPhone());



                Orders updatedOrder = ordersRepository.save(existingOrder);

                return updatedOrder;
            } else {
                throw new EntityNotFoundException("Order not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteOrder(Long id) {
        try {

            ordersRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
