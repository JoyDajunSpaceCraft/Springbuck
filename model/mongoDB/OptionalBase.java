package com.example.springbuckdemo.model.mongoDB;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Document
@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionalBase {
  @Id
  @Field("category_number")
  private Long categoryNumber;
  private String name;
  // TODO 每一种咖啡对应的有些地方是不一样的 比如Espresso & Shot Options对不同咖啡有不同选择

  @Field("unit_of_measure")
  private String unitOfMeasure;

}
