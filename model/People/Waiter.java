package com.example.springbuckdemo.model.People;


import com.example.springbuckdemo.model.BaseEntity;
import com.example.springbuckdemo.model.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="T_WAITER")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Waiter extends BaseEntity implements Serializable {
  private String name;
  @OneToMany(targetEntity = Order.class, mappedBy = "waiter")
  @JsonIgnoreProperties("waiter")
  private List<Order> orders;
}
