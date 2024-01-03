package br.com.fiap.tech.challenge.adapter.fixture;

import br.com.fiap.tech.challenge.adapter.dto.CartItemDTO;
import org.instancio.Instancio;
import org.instancio.Model;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.adapter.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.adapter.fixture.ProductDTOFixture.createSandwichDTOModel;
import static org.instancio.Select.field;

public class CartItemDTOFixture {

    public static Model<CartItemDTO> createCartItemDTOModel() {
        return Instancio.of(CartItemDTO.class)
                .set(field(CartItemDTO::getProduct), create(createSandwichDTOModel()))
                .set(field(CartItemDTO::getQuantity), 1)
                .set(field(CartItemDTO::getTotal), BigDecimal.valueOf(5.00))
                .set(field(CartItemDTO::getSubtotal), BigDecimal.valueOf(5.00))
                .set(field(CartItemDTO::getDiscount), BigDecimal.valueOf(0.00))
                .toModel();
    }
}
