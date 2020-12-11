package com.example.springbuckdemo.repository;

import com.example.springbuckdemo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//import org.springframework.data.r2dbc.repository.query.Query;
//import reactor.core.publisher.Mono;


public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
  //  @Query("select * from t_coffee where name=$1")
//  Mono<Coffee> findByName(String name);
//  Mono<Coffee> insert(0)
  List<Coffee> findByNameInOrderById(List<String> list);// 这里的in表示传入名字的列表，返回结果需要按照id来显示
  Coffee findByName(String name);
}
