package br.com.fiap.tech.challenge.application.mapper;

import br.com.fiap.tech.challenge.enterprise.valueobject.Image;
import br.com.fiap.tech.challenge.enterprise.valueobject.Price;
import org.mapstruct.Named;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

public interface CommonMapper {

    @Named("getPrice")
    static Price map(BigDecimal source) {
        return Price.of(makeMoney(source));
    }

    @Named("getImage")
    static Image map(String source) {
        return Image.of(source);
    }
}
