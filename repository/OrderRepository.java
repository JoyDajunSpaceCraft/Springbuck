package com.example.springbuckdemo.repository;


import com.example.springbuckdemo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
  Integer deleteCustomerOrderById(Long id);
  Order getOrderByCustomer(String customer);
}
