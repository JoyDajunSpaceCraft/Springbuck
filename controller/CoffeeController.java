package com.example.springbuckdemo.controller;


import com.example.springbuckdemo.controller.exception.FormValidationException;
import com.example.springbuckdemo.model.Coffee;
import com.example.springbuckdemo.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import com.example.springbuckdemo.controller.request.NewCoffeeRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
@Slf4j
public class CoffeeController {

  @Autowired
  private CoffeeService coffeeService;

//  @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
//  @ResponseStatus(HttpStatus.CREATED)
//  public Coffee addJsonCoffeeWithoutBindingResult(@Valid @RequestBody NewCoffeeRequest newCoffee) {
//    return coffeeService.saveCoffee(newCoffee.getName(), newCoffee.getPrice(), newCoffee.getFormCode());
//  }


  @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
//  @ResponseBody
  public Coffee addCoffee(@RequestBody NewCoffeeRequest newCoffeeRequest,
                          BindingResult result) {
    if (result.hasErrors()) {
      log.warn("Binding Errors: {}", result);
      throw new FormValidationException(result);
    }
    return coffeeService.saveCoffee(newCoffeeRequest.getName(), newCoffeeRequest.getPrice(), newCoffeeRequest.getFormCode());
  }

  @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  public Coffee addJsonCoffee(@Valid @RequestBody NewCoffeeRequest newCoffeeRequest,
                              BindingResult result) {
    if (result.hasErrors()) {
      log.warn("Binding Errors: {}", result);
      throw new ValidationException(result.toString());
    }
    return coffeeService.saveCoffee(newCoffeeRequest.getName(), newCoffeeRequest.getPrice(),newCoffeeRequest.getFormCode());
  }

  /*
  读入前端传入的文件，保证输入格式是每行按照
   coffeename 金额不含货币单位 coffee冷热
   来写这个txt文件
   */
  @PostMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public List<Coffee> batchAddCoffee(@RequestParam("txtfile") MultipartFile file) {
    List<Coffee> coffees = new ArrayList<>();
    if (!file.isEmpty()) {
      BufferedReader reader = null;
      try {
        reader = new BufferedReader(
          new InputStreamReader(file.getInputStream())
        );
        String str;
        while ((str = reader.readLine()) != null) {
          String[] arr = StringUtils.split(str, " ");
          if (arr != null && arr.length == 3) {
            coffees.add(coffeeService.saveCoffee(arr[0],
              Money.of(CurrencyUnit.of("CNY"), NumberUtils.createBigDecimal(arr[2])), arr[2]));
          }
        }
      } catch (IOException e) {
        log.error("exception in getting input file", e);
      } finally {
        IOUtils.closeQuietly(reader);
      }
    }
    return coffees;
  }

  @GetMapping(path = "/", params = "!name")
  public List<Coffee> getAll() {
    return coffeeService.getAllCoffee();
  }

  @GetMapping(path = "/{id}")
  public Coffee getById(@PathVariable Long id) {
    Coffee coffee = coffeeService.getCoffee(id);
    log.info("Coffee {}:", coffee);
    return coffee;
  }

  @GetMapping(path = "/", params = "name")
  public Coffee getByName(@RequestParam String name) {

    return coffeeService.getCoffee(name);
  }

}
