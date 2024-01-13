package br.com.fiap.tech.challenge.customer.launcher.fixture;

import br.com.fiap.tech.challenge.driven.product.response.ProductResponse;
import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.net.URL;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductResponseFixture {

    public static Model<ProductResponse> beverageModel() {
        return Instancio.of(ProductResponse.class)
                .generate(field(ProductResponse::getId), gen -> gen.text().uuid())
                .generate(field(ProductResponse::getImage), gen -> gen.net().url().as(URL::toString))
                .generate(field(ProductResponse::getPrice), gen -> gen.math().bigDecimal())
                .set(field(ProductResponse::getName), "Bebida")
                .set(field(ProductResponse::getCategory), ProductCategory.BEVERAGE)
                .set(field(ProductResponse::getEnabled), true)
                .toModel();
    }
}
