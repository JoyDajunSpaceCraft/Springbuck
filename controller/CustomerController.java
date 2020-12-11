package com.example.springbuckdemo.controller;


import com.example.springbuckdemo.controller.exception.FormValidationException;
import com.example.springbuckdemo.controller.request.NewCustomerRequest;
import com.example.springbuckdemo.model.People.Customer;
import com.example.springbuckdemo.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)// 解决跨域问题 就是能够在不同端口进行跳转
@RequestMapping("/")
@Slf4j
public class CustomerController {
  @Autowired
  private CustomerService customerService;

  @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Customer createCustomer(@RequestBody NewCustomerRequest newCustomerRequest,
                                 BindingResult result) {
    if (result.hasErrors()) {
      log.warn("Binding error in axios:{}", result);
      throw new FormValidationException(result);
    }
    int expirIn = 1000;//过期时间

    return customerService.saveCustomer(
      newCustomerRequest.getName(),
      newCustomerRequest.getToken(),
      newCustomerRequest.getEmail(),
      newCustomerRequest.getPassword());
  }



  // 根据token获得 user
  @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Customer getByToken(@RequestParam String token) {
    return customerService.getCustomerByToken(token);
  }

  /*
  表示在网页获得的时候会先提交一个获取了token的请求
  通过token得到整个的
   */
//  @PostMapping(path="/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//  @ResponseStatus(HttpStatus.CREATED)
//  public Customer getCustomerByLocalStorage(String token){
//    Customer customer =  customerService.getCustomerByToken(token);
//    return customer;
//  }

}
