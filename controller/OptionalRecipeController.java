//package com.example.springbuckdemo.controller;
//
//
//import com.example.springbuckdemo.model.mongoDB.OptionalBase;
//import com.example.springbuckdemo.model.mongoDB.OptionalRecipe;
//import com.example.springbuckdemo.service.OptionalRecipeService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//
///*
//根据 name 找到所需要的menu的详细信息
//不知道能不能根据 查询语句获得信息
// */
//@RestController
//@RequestMapping("/menu/products")
//@Slf4j
//public class OptionalRecipeController {
//  @Autowired
//  private OptionalRecipeService optionalRecipeService;
//
//  @GetMapping(path = "/", params = "name")
//  public OptionalRecipe getByName(@RequestParam String name) {
//    return optionalRecipeService.findOptionalRecipeByName(name);
//  }
//
//}
