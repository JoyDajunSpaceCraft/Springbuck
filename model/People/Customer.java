package com.example.springbuckdemo.model.People;

import com.example.springbuckdemo.model.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name= "T_CUSTOMER")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity implements Serializable {
  private String name;
  private String token;
  private String password;
  private String email;
  private Long expirein;
}
