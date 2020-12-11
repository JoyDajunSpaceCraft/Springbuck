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
@Table(name="T_BARISTA")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Barista extends BaseEntity implements Serializable {
  private String name;
  @OneToMany(targetEntity = Order.class, mappedBy = "barista")
  @JsonIgnoreProperties("barista")
  private List<Order> orders;
}
