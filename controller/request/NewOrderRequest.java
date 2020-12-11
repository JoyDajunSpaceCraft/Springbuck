package com.example.springbuckdemo.controller.request;


import com.example.springbuckdemo.model.People.Barista;
import com.example.springbuckdemo.model.People.Customer;
import com.example.springbuckdemo.model.People.Waiter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.Money;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@ToString
public class NewOrderRequest {
  private List<String> items;

  @NotEmpty
  private Long waiter;

  @NotEmpty
  private Long barista;

  @NotEmpty
  private Long customer;

  private Integer discount;
}
