package com.example.springbuckdemo.model;

//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.example.springbuckdemo.serializer.MoneyDeserializer;
//import com.example.springbuckdemo.serializer.MoneySerializer;
import lombok.*;
import org.joda.money.Money;
import org.hibernate.annotations.Type;


import javax.persistence.Table;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Table(name = "T_COFFEE")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Coffee extends BaseEntity implements Serializable {
  private String name;

  @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
  parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
  private Money price;

  private String formCode;
}
