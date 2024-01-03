package br.com.fiap.tech.challenge.adapter.fixture;

import br.com.fiap.tech.challenge.adapter.dto.CartDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.math.BigDecimal;
import java.util.List;

import static br.com.fiap.tech.challenge.adapter.fixture.CartFixture.UUID_CART;
import static br.com.fiap.tech.challenge.adapter.fixture.CartItemDTOFixture.createCartItemDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CustomerDTOFixture.enabledCustomerDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.Fixture.create;
import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartDTOFixture {

    public static Model<CartDTO> createCartDTOModel() {
        var items = List.of(create(createCartItemDTOModel()));

        return Instancio.of(CartDTO.class)
                .set(field(CartDTO::getId), UUID_CART.toString())
                .set(field(CartDTO::getTotal), BigDecimal.valueOf(5.00))
                .set(field(CartDTO::getSubtotal), BigDecimal.valueOf(5.00))
                .set(field(CartDTO::getDiscount), BigDecimal.valueOf(0.00))
                .set(field(CartDTO::getCustomer), create(enabledCustomerDTOModel()))
                .set(field(CartDTO::getItems), items)
                .toModel();
    }

}
