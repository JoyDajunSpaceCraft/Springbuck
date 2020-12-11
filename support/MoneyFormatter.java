package com.example.springbuckdemo.support;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;


/**
 * 这里是作为校验 客户端发送的请求是否准确而使用的 Formatter
 * 需要实现两个函数，printer 和parse 需要额外的Locale
 * public interface Printer<T> {
 *     String print(T var1, Locale var2);
 * }
 */
@Component
public class MoneyFormatter implements Formatter<Money> {
  @Override
  public Money parse(String text, Locale locale) throws ParseException {
    if (NumberUtils.isParsable((text))) {
      // 这里是实现将写入的string类型转换为money类
      return Money.of(CurrencyUnit.of("CNY"), NumberUtils.createBigDecimal(text));
    } else if (StringUtils.isNotEmpty(text)) {
      // 如果输入的值不是空而且能够用空格分开需要将获得的值
      String[] split = StringUtils.split(text, " ");
      if (split != null && split.length == 2) {
        return Money.of(CurrencyUnit.of(split[0]),
          NumberUtils.createBigDecimal((split[1])));
      }
      else{
        throw new ParseException(text, 0);
      }
    }
    throw new ParseException(text, 0);
  }

  @Override
  public String print(Money money, Locale locale) {
    // 通过money获得当前的金钱单位的code
    if(money == null){
      return null;
    }
    return money.getCurrencyUnit().getCode() + " " + money.getAmount();
  }
}
