package com.example.springbuckdemo.repository;

import com.example.springbuckdemo.model.People.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  // TODO 需要实现的是能通过用户名显示这个用户曾经有多少订单
//  Customer findByName(String username);
  Customer findByToken(String token);
//  Customer findByEmail(String email);
//  Customer save(Date date);
}
