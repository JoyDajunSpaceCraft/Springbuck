package com.example.springbuckdemo.service;


import com.example.springbuckdemo.model.People.Waiter;
import com.example.springbuckdemo.repository.WaiterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@CacheConfig(cacheNames = "WaiterCache")
public class WaiterService {

  @Autowired
  private WaiterRepository waiterRepository;

  public Waiter saveWaiter(String name){
    return waiterRepository.save(Waiter.builder().name(name).build());
  }

  public Waiter getByName(String name){
    return waiterRepository.findByName(name);
  }

  public Waiter getWaiterById(Long id){
    return waiterRepository.getOne(id);
  }

}
