package com.example.springbuckdemo.service;


import com.example.springbuckdemo.model.Coffee;
import com.example.springbuckdemo.model.Order;
import com.example.springbuckdemo.model.OrderState;
import com.example.springbuckdemo.model.People.Barista;
import com.example.springbuckdemo.model.People.Customer;
import com.example.springbuckdemo.model.People.Waiter;
import com.example.springbuckdemo.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Service
@Cacheable(cacheNames = "OrderCache")
public class OrderService {
  @Autowired
  private OrderRepository orderRepository;


  public Order createOrder(Waiter waiter,
                         Barista barista,
                         Customer customer,
                         Integer discont,
                         Money amount,
                         Coffee... coffee
  ) {
    Order order = Order.builder()
      .customer(customer)
      .barista(barista)
      .waiter(waiter)
      .items(new ArrayList<>(Arrays.asList(coffee)))
      .state(OrderState.INIT)
      .amount(amount)
      .discount(discont).build();
    Order savedOrder = orderRepository.save(order);
    log.info("New order :{}", savedOrder);
    return savedOrder;
  }



  public boolean updateState(Order order, OrderState state) {
    if (state.compareTo(order.getState()) <= 0) {
      log.warn("Wrong State:{},{}", state, order.getState());
      return false;
    }
    order.setState(state);
    orderRepository.save(order);
    log.info("Update State:{}", order);
    return true;
  }

//  deleteCustomerOrderById(Long id);
//
//  Order getOrderByCustomer(String customer);

}
