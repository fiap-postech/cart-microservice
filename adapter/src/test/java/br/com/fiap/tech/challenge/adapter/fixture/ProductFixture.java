package br.com.fiap.tech.challenge.adapter.fixture;


import br.com.fiap.tech.challenge.enterprise.entity.Sandwich;
import br.com.fiap.tech.challenge.enterprise.valueobject.Image;
import br.com.fiap.tech.challenge.enterprise.valueobject.Price;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;
import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class ProductFixture {

    public static Model<Sandwich> createSandwichModel() {
        return Instancio.of(Sandwich.class)
                .set(field(Sandwich::uuid), UUID.fromString("82736436-9ea5-45d1-81a4-31cba447566e"))
                .set(field(Sandwich::name), "Hamburguer Tripo X")
                .set(field(Sandwich::description), "Um belo sanduíche")
                .set(field(Sandwich::image), Image.of("http://localhost:8888/lanche.png"))
                .set(field(Sandwich::price), Price.of(makeMoney(BigDecimal.valueOf(5.00))))
                .set(field(Sandwich::enabled), Boolean.TRUE)
                .toModel();
    }
}
