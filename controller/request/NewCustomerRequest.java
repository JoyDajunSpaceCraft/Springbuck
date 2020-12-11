package com.example.springbuckdemo.controller.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class NewCustomerRequest {
  @NotNull
  @Size(max=10,min=5,message = "字段长度要在5-10之间")
  private String password;

  @Pattern(regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$",message = "不满足邮箱正则表达式")
  private String email;

  @NotEmpty
  private String name;

  @NotEmpty
  private String token;
}
