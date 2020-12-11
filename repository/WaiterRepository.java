package com.example.springbuckdemo.repository;

import com.example.springbuckdemo.model.People.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaiterRepository extends JpaRepository<Waiter, Long> {
  Waiter findByName(String name);
}
