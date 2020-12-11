package com.example.springbuckdemo.controller;


import com.example.springbuckdemo.controller.request.NewOrderRequest;
import com.example.springbuckdemo.model.Coffee;
import com.example.springbuckdemo.model.Order;
import com.example.springbuckdemo.model.People.Barista;
import com.example.springbuckdemo.model.People.Customer;
import com.example.springbuckdemo.model.People.Waiter;
import com.example.springbuckdemo.service.*;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.tools.jconsole.JConsole;

import java.awt.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

  @Autowired
  private OrderService orderService;
  @Autowired
  private CoffeeService coffeeService;
  @Autowired
  private WaiterService waiterService;
  @Autowired
  private BaristaService baristaService;
  @Autowired
  private CustomerService customerService;

  @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Order create(@RequestBody NewOrderRequest orderRequest, BindingResult result) {
    log.info("Receive new Order {}", orderRequest);
    Coffee[] coffeeList = coffeeService.getCoffeeByName(orderRequest.getItems())
      .toArray(new Coffee[]{});
    Waiter w = waiterService.getWaiterById(orderRequest.getWaiter());
    Barista b = baristaService.getBaristaById(orderRequest.getBarista());
    Customer c = customerService.getCustomerById(orderRequest.getCustomer());
    BigDecimal bigAmount = new BigDecimal("0.00");
    for(Coffee coffee:coffeeList){
//      log.info("coffee money:{}", coffee.getPrice().getAmount().getClass().getName());
//      BigDecimal newAmount = coffee.getPrice().getAmount();
      bigAmount = bigAmount.add(coffee.getPrice().getAmount());
      log.info("every time money{}",bigAmount);
    }
    Money amount = Money.of(CurrencyUnit.of("CNY"),bigAmount);

    log.info("amount:{}", amount.getAmount());

    return orderService.createOrder(w,
      b,
      c,
      orderRequest.getDiscount(),
      amount,
      coffeeList);
  }
}
