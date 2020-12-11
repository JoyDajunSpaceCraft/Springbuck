package com.example.springbuckdemo.model.mongoDB;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Size {
  @Id
  @Field("size_code_state")
  private SizeCodeState sizeCodeState;

  private String name;

  private String sku;
}
