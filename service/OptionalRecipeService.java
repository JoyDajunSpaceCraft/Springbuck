//package com.example.springbuckdemo.service;
//
//
//import com.example.springbuckdemo.model.mongoDB.OptionalRecipe;
//import com.example.springbuckdemo.repository.OptionalRecipeRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//@CacheConfig(cacheNames = "OptionalRecipeCache")
//public class OptionalRecipeService {
//
//  @Autowired
//  private OptionalRecipeRepository repository;
//
//  @Cacheable
//  public OptionalRecipe findOptionalRecipeByName(String name) {
//    return repository.findAllByName(name);
//  }
//}
