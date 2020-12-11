package com.example.springbuckdemo.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent// 注册序列化组件
public class MoneyDeserializer extends StdDeserializer<Money> {
  protected  MoneyDeserializer(){
    super(Money.class);//表明是针对Money类的反序列化
  }

  @Override
  public Money deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return Money.ofMinor(CurrencyUnit.of("CNY"), p.getLongValue());
  }
}
