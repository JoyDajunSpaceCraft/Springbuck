package com.example.springbuckdemo.model;

import com.example.springbuckdemo.model.People.Barista;
import com.example.springbuckdemo.model.People.Customer;
import com.example.springbuckdemo.model.People.Waiter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "T_ORDER")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity implements Serializable {

  @ManyToMany
  @JoinTable(name = "T_ORDER_COFFEE")
  @OrderBy("id")
  private List<Coffee> items;


  // 在数据库中开启customer_id字段，代表的是Customer中的id
  @ManyToOne
  @JoinColumn(name="customer_id", referencedColumnName = "id")
  @JsonIgnoreProperties("order")
  @ToString.Exclude // 防止出现无限次数的调用
  private Customer customer;

  @Enumerated
  @Column(nullable = false)
  private OrderState state;
  private Integer discount;

  @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
  parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})// 订单中包含的所有内容的价格
  private Money amount;

  @ManyToOne
  @JoinColumn(name="waiter_id", referencedColumnName = "id")
  @JsonIgnoreProperties("order")
  @ToString.Exclude
  private Waiter waiter;

  @ManyToOne
  @JoinColumn(name="barista_id", referencedColumnName = "id")
  @JsonIgnoreProperties("order")
  @ToString.Exclude
  private Barista barista;

}
