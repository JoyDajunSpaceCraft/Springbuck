package com.example.springbuckdemo.service;


import com.example.springbuckdemo.model.People.Customer;
import com.example.springbuckdemo.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@CacheConfig(cacheNames = "CustomerCache")
public class CustomerService {
  @Autowired
  private CustomerRepository customerRepository;

  // TODO 需要生成Token HOW？？？
  // TODO 过期时间设置问题
  public Customer saveCustomer(String name, String token, String email, String password) {
    return customerRepository.save(Customer.builder().name(name).token(token).password(password).email(email).expirein(1000l).build());
  }

  @Cacheable
  public Customer getCustomerByToken(String token) {
    return customerRepository.findByToken(token);
  }


  @Cacheable
  public Customer getCustomerById(Long id){
    return customerRepository.getOne(id);
  }
//  @Cacheable
//  public Customer getCustomerByName(String name) {
//    return customerRepository.findByName(name);
//  }
//
//  @Cacheable
//  public Customer getCustomerByEmail(String email){
//    return customerRepository.findByEmail(email);
//  }


}
