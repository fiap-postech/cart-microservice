package br.com.fiap.tech.challenge.adapter.fixture;


import br.com.fiap.tech.challenge.adapter.dto.ProductDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.enterprise.enums.ProductCategory.SANDWICH;
import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class ProductDTOFixture {

    public static Model<ProductDTO> createSandwichDTOModel() {
        return Instancio.of(ProductDTO.class)
                .set(field(ProductDTO::getId), "82736436-9ea5-45d1-81a4-31cba447566e")
                .set(field(ProductDTO::getName), "Hamburguer Tripo X")
                .set(field(ProductDTO::getDescription), "Um belo sanduíche")
                .set(field(ProductDTO::getImage), "http://localhost:8888/lanche.png")
                .set(field(ProductDTO::getPrice), new BigDecimal ("5.00"))
                .set(field(ProductDTO::getFullPrice), new BigDecimal ("5.00"))
                .set(field(ProductDTO::getDiscount), new BigDecimal ("0.00"))
                .set(field(ProductDTO::getCategory), SANDWICH)
                .set(field(ProductDTO::getEnabled), Boolean.TRUE)
                .toModel();
    }
}
