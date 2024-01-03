package br.com.fiap.tech.challenge.adapter.mapping;

import br.com.fiap.tech.challenge.enterprise.valueobject.Discount;
import br.com.fiap.tech.challenge.enterprise.valueobject.Image;
import br.com.fiap.tech.challenge.enterprise.valueobject.Price;
import br.com.fiap.tech.challenge.enterprise.valueobject.Quantity;
import org.javamoney.moneta.Money;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.fiap.tech.challenge.util.MoneyConstants.CURRENCY_CODE;
import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

public interface CommonMapper {

    @Named("generateUuid")
    static UUID generateUuid(String uuid) {
        return UUID.fromString(uuid);
    }

    @Named("getPrice")
    static Price map(BigDecimal source) {
        return Price.of(makeMoney(source));
    }

    @Named("getDiscount")
    static Discount mapDiscount(BigDecimal source) {
        return Discount.of(makeMoney(source));
    }

    @Named("mapBigDecimalToMoney")
    static Money mapToMoney(BigDecimal source) {
        return Money.of(source, CURRENCY_CODE);
    }

    @Named("getImage")
    static Image map(String source) {
        return Image.of(source);
    }

    @Named("mapMoneyToBigDecimal")
    static BigDecimal map(Money money) {
        return money.getNumberStripped();
    }

    @Named("getQuantityVO")
    static Quantity mapQuantity(int quantity){
        return Quantity.of(quantity);
    }
}
