package com.example.demo.controller;

import com.example.demo.entity.Customers;
import com.example.demo.entity.Orders;
import com.example.demo.service.OrdersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class OrdersController {
    @Autowired
    private  final OrdersService ordersService;


    //get list order
    @GetMapping(value = "/order")
    public List<Orders> getListUser() {

        return ordersService.getLisOrder();
    }

    //get order by id
    @GetMapping(value = "/order/{id}")
    public Orders getListUser(@PathVariable Long id) {

        return ordersService.getOrderById(id);
    }

    // create order
    @PostMapping(value = "/order")
    public Orders createCustomer(@RequestBody Orders orders) {

        return ordersService.createOrder(orders);
    }

    //update order by id
    @PutMapping(value = "/order/{id}")
    public Orders updateCustomerById(@PathVariable Long id, @RequestBody Orders orders) {

        return ordersService.updateOrderById(id, orders);
    }

    ////delete order by id
    @DeleteMapping("/order/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        ordersService.deleteOrder(id);
    }
}
