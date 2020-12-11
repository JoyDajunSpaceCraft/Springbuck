package com.example.springbuckdemo.converter;

//import org.bson.Document;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;


/**
 * 将Mongodb中document 类型转化为 money类型 只有调用
 */
@ReadingConverter
public class MoneyReadConverter implements Converter<Long, Money> {
    @Override
    public Money convert(Long along) {
//        Document money = (Document) source.get("money");
//        double amount = Double.parseDouble(money.getString("amount"));
//        String currency = ((Document) money.get("currency")).getString("code");
//        return Money.of(CurrencyUnit.of(currency), amount);
      return Money.ofMinor(CurrencyUnit.of("CNY"), along);
    }
}
