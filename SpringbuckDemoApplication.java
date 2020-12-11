package com.example.springbuckdemo;

import com.example.springbuckdemo.controller.PerformanceInteceptor;
import com.example.springbuckdemo.converter.MoneyReadConverter;
import com.example.springbuckdemo.model.Coffee;
//import com.mongodb.client.result.UpdateResult;
import com.example.springbuckdemo.service.CoffeeService;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

//import static org.springframework.data.mongodb.core.query.Criteria.where;
//import static org.springframework.data.mongodb.core.query.Query.query;

@SpringBootApplication
@EnableJpaRepositories("com.example.springbuckdemo.repository")
@EnableCaching
@Slf4j
//public class SpringbuckDemoApplication implements WebMvcConfigurer {
public class SpringbuckDemoApplication implements  WebMvcConfigurer{
//  @Autowired
//  private MongoTemplate mongoTemplate;

  public static void main(String[] args) {
    SpringApplication.run(SpringbuckDemoApplication.class, args);
  }

//  @Bean
  //自定义转化Bean 在converter 文件夹中
//  public MongoCustomConversions mongoCustomConversions() {
//    return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter()));
//  }


  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new PerformanceInteceptor())
      .addPathPatterns("/coffee/**").addPathPatterns("/custom/**");
  }

  @Bean
  public Hibernate5Module hibernate5Module() {
    return new Hibernate5Module();
  }
  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
    return builder -> {
      builder.indentOutput(true);
      builder.timeZone(TimeZone.getTimeZone("Asia/Shanghai"));// 这里的时区应该是要+800
    log.info("sds");
    };
  }
//    Coffee espresso = Coffee.builder()
//      .name("espresso")
//      .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
//      .formCode("hot").build();
//    Coffee saved = mongoTemplate.save(espresso);
//    log.info("Coffee {}", saved);
//
//    List<Coffee> list = mongoTemplate.find(
//      Query.query(Criteria.where("name").is("espresso")), Coffee.class);
//    log.info("Find {} Coffee", list.size());
//    list.forEach(c -> log.info("Coffee {}", c));
//
//    Thread.sleep(1000); // 为了看更新时间
//    UpdateResult result = mongoTemplate.updateFirst(query(where("name").is("espresso")),
//      new Update().set("price", Money.ofMajor(CurrencyUnit.of("CNY"), 30))
//        .currentDate("updateTime"),
//      Coffee.class);
//    log.info("Update Result: {}", result.getModifiedCount());
//    Coffee updateOne = mongoTemplate.findById(saved.getId(), Coffee.class);
//    log.info("Update Result: {}", updateOne);
//
//    mongoTemplate.remove(updateOne);


}



