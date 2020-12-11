package com.example.springbuckdemo.model.mongoDB;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Document

public class Products implements Serializable {
  @Id
  @Field("product_number")
  private Long productNumber;

  @Field("form_name")
  private String formName;

  @Field("form_code_state")
  private FormCodeState formCodeState;

  private String availability;

  @DBRef
  private List<Size> size;
}
