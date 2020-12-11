package com.example.springbuckdemo.service;


import com.example.springbuckdemo.model.Coffee;
import com.example.springbuckdemo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
@CacheConfig(cacheNames = "CoffeeCache")
public class CoffeeService {
  @Autowired(required = true)
  private CoffeeRepository coffeeRepository;

  public Coffee saveCoffee(String name, Money price, String formCode) {
    return coffeeRepository.save(Coffee.builder().name(name).price(price).formCode(formCode).build());
  }

  @Cacheable // 传入缓存
  public List<Coffee> getAllCoffee() {
    return coffeeRepository.findAll(Sort.by("id"));
  }

  public Coffee getCoffee(Long id) {
    return coffeeRepository.getOne(id);
  }

  public Coffee getCoffee(String name) {
    return coffeeRepository.findByName(name);
  }

  public long getCoffeeCount() {
    return coffeeRepository.count();
  }

  public List<Coffee> getCoffeeByName(List<String> names) {
    return coffeeRepository.findByNameInOrderById(names);
  }

  public List<Coffee> findAllCoffee() {
    return coffeeRepository.findAll();
  }
}
