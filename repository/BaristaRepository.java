package com.example.springbuckdemo.repository;

import com.example.springbuckdemo.model.People.Barista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaristaRepository  extends JpaRepository<Barista, Long> {
  Barista findByName(String name);
}


