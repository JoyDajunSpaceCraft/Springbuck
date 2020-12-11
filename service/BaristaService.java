package com.example.springbuckdemo.service;


import com.example.springbuckdemo.model.People.Barista;
import com.example.springbuckdemo.model.People.Customer;
import com.example.springbuckdemo.repository.BaristaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@CacheConfig(cacheNames = "BaristaCache")
public class BaristaService {

  @Autowired
  private BaristaRepository baristaRepository;


  public Barista saveBarista(String name) {
    return baristaRepository.save(Barista.builder().name(name).build());
  }

  @Cacheable
  public Barista getBaristaByName(String name) {
    return baristaRepository.findByName(name);
  }


  public Barista getBaristaById(Long id){
    return baristaRepository.getOne(id);
  }

}
